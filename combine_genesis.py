import json
import os

parts = []
for i in range(1, 6):
    part_path = f"app/src/main/assets/bible/part{i}.json"
    if os.path.exists(part_path):
        with open(part_path, "r", encoding="utf-8") as f:
            parts.extend(json.load(f))
    else:
        print(f"Missing {part_path}")

print(f"Total chapters loaded: {len(parts)}")

genesis_data = {
    "title": "ኦሪት ዘፍጥረት",
    "abbv": "ዘፍ",
    "chapters": parts
}

with open("app/src/main/assets/bible/amharic_genesis.json", "w", encoding="utf-8") as f:
    json.dump(genesis_data, f, ensure_ascii=False, indent=2)

print("Saved amharic_genesis.json successfully with", len(genesis_data["chapters"]), "chapters!")
