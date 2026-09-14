import json
import os

os.makedirs("app/src/main/assets/bible", exist_ok=True)

# We will write the full Amharic Genesis JSON
with open("app/src/main/assets/bible/amharic_genesis.json", "w", encoding="utf-8") as f:
    f.write('{"title": "ኦሪት ዘፍጥረት", "abbv": "ዘፍ", "chapters": []}')

print("Initialized JSON file")
