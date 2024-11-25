summon bat ~ ~ ~ {Silent:1b,NoAI:1b,Invulnerable:1b,Tags:['blackwhip_hook']}
power grant @e[type=bat,distance=0,nbt={Tags:['blackwhip_hook']}] quirks:blackwhip/whip_swing_bat_effects
execute store result score @e[type=bat,distance=0,nbt={Tags:['blackwhip_hook']}] BlackwhipUUID run data get entity @s UUID[0]