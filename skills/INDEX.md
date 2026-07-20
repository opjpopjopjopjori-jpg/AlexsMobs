# Skills Index

## 📚 Complete Reference for AlexsMobs Animation Development

This is a comprehensive knowledge base built from fixing 33 bugs across 29 model files in the AlexsMobs project.

---

## 🗂️ File Structure

```
skills/
├── INDEX.md                    ← You are here
├── animation-bugs.md           ← Animation-specific bugs & fixes
├── rendering-bugs.md           ← Rendering bugs & fixes
├── logic-bugs.md               ← Logic bugs & fixes
├── performance-issues.md       ← Performance optimizations
├── best-practices.md           ← Best practices & patterns
├── common-pitfalls.md          ← Common mistakes to avoid
├── testing-patterns.md         ← Testing strategies & tools
├── build-issues.md             ← Build system & deployment
└── lessons-learned.md          ← Key insights & takeaways
```

---

## 🚀 Quick Start

### New to AlexsMobs Animation?

1. **Read**: `best-practices.md` — Learn the patterns
2. **Study**: `animation-bugs.md` — Understand common issues
3. **Avoid**: `common-pitfalls.md` — Don't repeat mistakes
4. **Test**: `testing-patterns.md` — Validate your work

### Fixing a Bug?

1. **Identify**: Which category?
   - Animation issue → `animation-bugs.md`
   - Visual glitch → `rendering-bugs.md`
   - Logic error → `logic-bugs.md`
   - Slow performance → `performance-issues.md`

2. **Search**: Use the bug name or symptom
3. **Apply**: Follow the fix pattern
4. **Verify**: Use testing patterns

### Building & Deploying?

1. **Read**: `build-issues.md` — Understand the build system
2. **Check**: Java 17, 3GB+ RAM, Forge 1.20.1
3. **Build**: `./gradlew build`
4. **Deploy**: Copy jar to mods folder

---

## 📊 Bug Categories

### Animation Bugs (animation-bugs.md)
- Keyframe stack corruption
- Wrong axis usage
- Missing resetToDefaultPose
- Duplicate animations
- Progress variable issues
- Missing fields
- Keyframe balance
- State transition guards
- Breath animation patterns

### Rendering Bugs (rendering-bugs.md)
- Scale not reset
- Wrong head scale value
- Baby scale not applied
- setShouldScaleChildren missing
- Multiple scales without reset
- Render order issues
- Missing parts() override
- Incomplete getAllParts()

### Logic Bugs (logic-bugs.md)
- Hardcoded boolean values
- Float equality checks
- Static mutable fields
- Raw entity field access
- Unconditional animation
- Overwriting instead of additive
- Missing state guards
- Incorrect progress calculation
- Division by zero risk
- Missing partial tick interpolation

### Performance Issues (performance-issues.md)
- Redundant resetToDefaultPose
- Magic numbers
- Duplicate calculations
- Expensive operations in setupAnim
- Unnecessary object creation
- Redundant conditional checks
- Inefficient loop patterns
- Excessive method calls
- Unused imports
- Inefficient string operations

---

## 🛠️ Tools & Scripts

### Static Analysis (testing-patterns.md)
```bash
./run-all-checks.sh  # 23 automated checks
```

### Stress Testing (testing-patterns.md)
```bash
python3 stress-test.py  # Summon all entities
```

### Build Verification (build-issues.md)
```bash
./gradlew clean build  # Clean build
```

### Regression Testing (testing-patterns.md)
```bash
./regression-test.sh  # Full test suite
```

---

## 🎯 Common Tasks

### "I need to add a new animation"

1. Read `best-practices.md` → Keyframe Patterns
2. Follow the standard structure
3. Verify keyframe balance
4. Test in-game

### "Entity animation looks wrong"

1. Check `animation-bugs.md` → Wrong Axis Usage
2. Verify axis mapping (X/Y/Z)
3. Check for standing state exceptions
4. Test all states

### "Baby entity looks weird"

1. Check `rendering-bugs.md` → Scale Not Reset
2. Verify scale reset in renderToBuffer
3. Check setShouldScaleChildren
4. Test baby and adult

### "Build is failing"

1. Check `build-issues.md` → Common Build Errors
2. Verify Java 17
3. Check dependencies
4. Try clean build

### "Animation is slow/jerky"

1. Check `performance-issues.md`
2. Look for redundant operations
3. Profile with debug tools
4. Optimize hot paths

---

## 📈 Statistics

### Bugs Fixed
- **Total**: 33 bugs
- **Files Modified**: 29
- **Lines Changed**: 41 additions, 45 deletions

### Bug Categories
- Critical: 2 (keyframe corruption)
- High: 7 (axis errors, scale issues)
- Medium: 17 (logic, state, progress)
- Performance: 7 (redundant operations)

### Testing
- Static Analysis: 23 checks
- Files Validated: 135
- Stress Test: All entities

---

## 🔍 Search Tips

### By Bug Type
```
"keyframe" → animation-bugs.md
"scale" → rendering-bugs.md
"progress" → logic-bugs.md
"performance" → performance-issues.md
```

### By File
```
"ModelGrizzlyBear" → animation-bugs.md, performance-issues.md
"ModelCrow" → animation-bugs.md, rendering-bugs.md
"ModelMurmurNeck" → logic-bugs.md
```

### By Symptom
```
"crash" → animation-bugs.md (keyframe corruption)
"visual glitch" → rendering-bugs.md
"jerky animation" → logic-bugs.md (interpolation)
"slow" → performance-issues.md
```

---

## 📝 Contributing

### Found a New Bug?

1. **Document**: Add to appropriate file
2. **Format**: Use the standard template:
   ```markdown
   ## N. Bug Name
   
   ### ❌ Problem
   ```java
   // Bad code
   ```
   
   ### ✅ Fix
   ```java
   // Good code
   ```
   
   ### Detection
   ```bash
   # How to find it
   ```
   
   ### Affected Files
   - List of files
   ```

3. **Test**: Verify the fix works
4. **Update**: Add to INDEX.md if new category

---

## 🎓 Learning Path

### Beginner
1. Read `best-practices.md`
2. Study `common-pitfalls.md`
3. Fix simple bugs (typos, missing semicolons)
4. Test in-game

### Intermediate
1. Read `animation-bugs.md`, `rendering-bugs.md`
2. Fix axis errors, scale issues
3. Write test scripts
4. Optimize performance

### Advanced
1. Read all files
2. Fix complex logic bugs
3. Design new animation systems
4. Mentor others

---

## 🔗 External Resources

### Official Documentation
- [Minecraft Forge Docs](https://docs.minecraftforge.net/)
- [Citadel Library](https://github.com/AlexModGuy/Citadel)
- [AlexsMobs Source](https://github.com/AlexModGuy/AlexsMobs)

### Tools
- [Blockbench](https://www.blockbench.net/) — 3D model editor
- [MCreator](https://mcreator.net/) — Mod development IDE
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/alexs-mobs) — Mod distribution

### Community
- [AlexsMobs Discord](https://discord.gg/alexsmobs)
- [Forge Forums](https://forums.minecraftforge.net/)
- [Reddit r/feedthebeast](https://www.reddit.com/r/feedthebeast/)

---

## 📞 Support

### Questions?
1. Search this index first
2. Check the appropriate file
3. Review examples
4. Test your understanding

### Stuck?
1. Re-read the relevant section
2. Check affected files for examples
3. Review testing patterns
4. Ask for help with specific context

---

## 🎯 Remember

> **"Static analysis is necessary but not sufficient. Always test in-game."**

> **"Document everything. Future you will thank you."**

> **"Small fixes can have big impact."**

> **"Ship early, iterate often."**

---

**Last Updated**: 2026-01-20  
**Version**: 1.0  
**Maintained by**: AlexsMobs Development Team
