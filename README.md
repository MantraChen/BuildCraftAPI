# BuildCraftAPI
The API for BuildCraft. Versions 7.2.0 and above.

This branch supplies the native Fabric 1.20.1 API. Remake by FelixMantra.

Facade add-ons can call `FacadeAPI.disableBlock(block)` and
`FacadeAPI.mapStateToStack(state, stack)` during common mod initialization,
before recipes load. The latter copies the item's NBT and normalizes its count
to one; an empty stack hides that state from creative entries and assembly.
These direct hooks replace the old Forge IMC messages. The implementation
discovers registered full-cube model states lazily and exposes them through
`FacadeAPI.registry`; `FacadeAPI.facadeItem` creates native NBT item stacks.
Phased facades accept one default and up to sixteen uniquely coloured states.
The first powered colour wins, otherwise the default (or first state) is shown.
