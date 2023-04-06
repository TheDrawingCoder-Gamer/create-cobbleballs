package net.bulbyvr.cobbleballs

import com.cobblemon.mod.common.Cobblemon
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import java.util.concurrent.CompletableFuture
import java.util.function.Supplier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import collection.mutable as mut
object PainfulRegister {
  private val allRegistries: mut.ListBuffer[RegistryKey[Registry[?]]] = collection.mutable.ListBuffer()
  val allRegistriesCompleted: CompletableFuture[Unit] = CompletableFuture[Unit]()

}
open class PainfulRegister[T](val key: RegistryKey[Registry[T]]) {
  import PainfulRegister.*
  val completed: CompletableFuture[Unit] = CompletableFuture[Unit]()
  protected val deferredRegistry: DeferredRegister[T] = DeferredRegister.create(Cobbleballs.MODID, key)
  protected val pendingRegistryEntries: mut.ListBuffer[RegistrySupplier[T]] = collection.mutable.ListBuffer()


  allRegistries.append(key.asInstanceOf[RegistryKey[Registry[?]]])
  completed.thenAccept { _ =>
    allRegistries.filterInPlace(_ != key)
    if (allRegistries.isEmpty) {
      allRegistriesCompleted.complete(())
    }
  }

  def queue[E <: T](name: String, block: Supplier[E]): RegistrySupplier[E] = {
    val entry = deferredRegistry.register(name, block)
    // nightmare
    pendingRegistryEntries.append(entry.asInstanceOf[RegistrySupplier[T]])
    entry.listen { _ =>
      pendingRegistryEntries.filterInPlace(_ != entry)
      if (pendingRegistryEntries.isEmpty) {
        completed.complete(())
      }
    }
    entry
  }

  def register(): Unit = {
    deferredRegistry.register()
  }
}
