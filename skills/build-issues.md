# Build Issues

## 1. NeoForge vs Forge (1.20.1)

### ✅ They Are the Same in 1.20.1

**Confusion**:
```gradle
minecraft 'net.neoforged:forge:1.20.1-47.1.65'
```
Looks like NeoForge, but it's actually **Forge**.

**History**:
- **Before 1.20.1**: `net.minecraftforge:forge`
- **In 1.20.1**: `net.neoforged:forge` (same thing, namespace change)
- **After 1.20.2**: NeoForge split into separate project

**Conclusion**: AlexsMobs 1.20.1 works on **Forge** ✅

---

## 2. Java Version

### ✅ Requires Java 17

**Check**:
```bash
java -version
# Should show: openjdk version "17.0.x"
```

**Install**:
```bash
# Windows
# Download from: https://adoptium.net/temurin/releases/?version=17

# Mac
brew install openjdk@17

# Linux
sudo apt install openjdk-17-jdk
```

**Common Error**:
```
> Could not determine java version from '21.0.1'.
```
**Fix**: Use Java 17, not 21

---

## 3. Gradle Memory

### ✅ Requires 3GB+ RAM

**gradle.properties**:
```properties
org.gradle.jvmargs=-Xmx3G
```

**Common Error**:
```
java.lang.OutOfMemoryError: Java heap space
```

**Fix**:
```properties
# Increase to 4GB
org.gradle.jvmargs=-Xmx4G
```

---

## 4. Dependencies

### ✅ Required Mods

**build.gradle**:
```gradle
dependencies {
    minecraft 'net.neoforged:forge:1.20.1-47.1.65'
    implementation fg.deobf("curse.maven:citadel-331936:5633260")
    compileOnly(fg.deobf("mezz.jei:jei-${jei_mc_version}-common-api:${jei_version}"))
}
```

**Required**:
- Citadel (331936:5633260)
- JEI (optional, for dev)

**Common Error**:
```
> Could not resolve curse.maven:citadel-331936:5633260
```

**Fix**:
- Check internet connection
- Verify CurseForge Maven is accessible
- Try different CurseForge file ID

---

## 5. Build Time

### ⏱️ Expected Duration

**First Build**:
- Download Gradle: 1-2 min
- Download Minecraft: 5-10 min
- Decompile: 5-10 min
- Download dependencies: 2-5 min
- Compile: 2-5 min
- **Total: 15-30 min**

**Subsequent Builds**:
- Compile: 2-5 min
- **Total: 3-7 min**

**Speed Up**:
```bash
# Use daemon (faster subsequent builds)
./gradlew build

# Skip tests (faster)
./gradlew build -x test

# Parallel compilation
./gradlew build --parallel
```

---

## 6. Common Build Errors

### ❌ Compilation Errors

**Error**:
```
error: cannot find symbol
  symbol:   variable prevBiteProgress
```

**Fix**: Check entity class for field name

---

**Error**:
```
error: incompatible types: int cannot be converted to float
```

**Fix**: Add `F` suffix
```java
float rideProgress = 1F;  // Not 1
```

---

**Error**:
```
error: ';' expected
```

**Fix**: Add semicolon
```java
animator.endKeyframe();  // Not animator.endKeyframe()
```

---

### ❌ Dependency Errors

**Error**:
```
> Could not resolve all files for configuration
```

**Fix**:
- Check internet
- Clear Gradle cache: `rm -rf ~/.gradle/caches`
- Update dependency versions

---

### ❌ Runtime Errors

**Error**:
```
java.lang.IllegalStateException: Keyframe stack corrupted
```

**Fix**: Check keyframe balance (starts == ends)

---

## 7. Deployment

### ✅ Copy to Server

```bash
# Build
./gradlew build

# Copy jar
cp build/libs/alexsmobs-*.jar /path/to/server/mods/

# Restart server
/path/to/server/restart.sh
```

---

## 8. Debugging

### ✅ Enable Debug Logging

**gradle.properties**:
```properties
org.gradle.logging.level=debug
```

**Command**:
```bash
./gradlew build --debug 2>&1 | tee debug.log
```

---

## 9. Clean Build

### ✅ When to Clean

**Clean when**:
- Changing dependencies
- Strange compilation errors
- Cached files corrupted

**Command**:
```bash
./gradlew clean build
```

---

## 10. CI/CD

### ✅ GitHub Actions

```yaml
# .github/workflows/build.yml
name: Build
on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      
      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'
      
      - name: Build
        run: ./gradlew build --no-daemon
      
      - name: Upload artifact
        uses: actions/upload-artifact@v4
        with:
          name: mod
          path: build/libs/*.jar
```
