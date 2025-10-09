particle minecraft:end_rod ^ ^ ^ 0 0 0 0.02 6 normal
particle minecraft:dust 1 1 18000000 2 ^0.7 ^-0.2 ^ 0.1 0 0.2 1 2 normal
particle minecraft:dust 18000000 18000000 1 2 ^-0.5 ^-0.2 ^ 0.1 0 0.2 1 2 normal
particle minecraft:dust 18000000 1 1 2 ^ ^0.5 ^ 0.1 0.2 0 0 2 normal
particle minecraft:dust 1 18000000 1 2 ^ ^-0.8 ^ 0.1 0.1 0 0 2 normal
playsound quirks:invisibility.blinded player @a[distance=..6,tag=!invisibility] ~ ~ ~ 0.1 2
execute at @a[distance=..0.4,sort=nearest] run effect give @s quirks:blinded 100 0 true