execute run power grant @e[tag=!somnambulist,distance=..2,sort=nearest] quirks:somnambulist/drowsy
execute at @a[distance=..3] run resource set @s quirks:somnambulist/drowsy_resource 50
particle minecraft:dust 18000000 0.85 18000000 3 ~ ~ ~ 0.3 0.3 0.3 0 3 normal
playsound quirks:somnambulist.mist player @a[distance=..6] ~ ~ ~ 0.5 1