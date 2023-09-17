import PIL.Image as pil

f = "playerRun.png"
spriteSheet = pil.open(f)

offset = 18

spriteHeight = 70
spriteWidth = 128
spriteWidthW = 60
spriteCount = 8

newSpriteSheet = pil.new("RGBA", (spriteWidthW * (spriteCount), spriteHeight))

for i in range(spriteCount):
    left = (spriteWidth * i) + (spriteWidthW/2) - offset
    right = left + spriteWidth*2 + offset
    sprite = spriteSheet.crop((left, spriteSheet.height - spriteHeight, right, spriteSheet.height))
    newSpriteSheet.paste(sprite, (spriteWidthW * i, 0))

newSpriteSheet.save("src/main/resources/assets/textures/" + f)
