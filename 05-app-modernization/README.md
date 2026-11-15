# Lesson 5: App modernization

**An old app, a new home** · Part 5 of 5 · [All lessons](../README.md)

Old apps get harder to run and to keep secure as Java and its libraries move on. In this lesson, you let Copilot move one to a new home: the latest Java and Spring Boot.

## The problem

This lesson's Trail Store is an old copy, on Java 8, Spring Boot 2.7 and JUnit 4. It still builds, but Spring Boot 2.7 stopped getting free updates in 2023, and many of its libraries have known vulnerabilities (CVEs). Move it to Java 25, Spring Boot 4 and JUnit Jupiter, without rewriting it.

The store must still show the same six products, and both tests must still check what they check now.

## Use

### GitHub Copilot modernization

A separate plugin, by Microsoft, that upgrades Java apps. By hand, an upgrade means chasing every renamed package, changed API and new library version. With the plugin, Copilot writes an upgrade plan, changes the code step by step, builds and tests after each step, and checks the libraries for known CVEs. It adds the **GitHub Copilot modernization** tool window on the left, and a **modernize-java-upgrade** agent to the chat. It works with any GitHub Copilot plan, and upgrades don't need an Azure account.

### Vulnerable dependencies

IntelliJ checks your libraries against known vulnerabilities, with data from Mend.io and OSV. It highlights vulnerable ones in `pom.xml`, and Code → Analyze Code → **Vulnerable Dependencies** lists them all. Use it to check the upgrade yourself: Copilot's own CVE check can miss some.

## Get started

- Open the `05-app-modernization` folder as its own project: choose File → Open, select the folder, and click **New Window**. The upgrade changes the whole project, so this keeps the other lessons out of it.
- In File → Project Structure → Project, set **SDK** to JDK 25.
- Check that it builds: in the terminal, run `.\mvnw.cmd verify` (on macOS and Linux, `./mvnw verify`). It passes on Java 25, but the code still targets Java 8.
- Install the plugin: in Settings → Plugins, search for **GitHub Copilot modernization**, and click **Install**. If IntelliJ asks, restart it.

With IntelliJ 2026.3, version 1.14.0 of the plugin can stop IntelliJ's terminal from starting ([known issue](https://github.com/microsoft/github-copilot-appmod/issues/213)). Until it's fixed, run `mvnw` in your system's terminal, and under the chat box, choose **Copilot**, not **Local**: the **Local** agent needs IntelliJ's terminal.

## Try it

1. **See what's vulnerable.** Choose Code → Analyze Code → **Vulnerable Dependencies**. Its **Security** tab lists the old libraries with known CVEs.

2. **Turn on the plugin's tools.** In the chat box, click **Configure tools** (the sliders icon). Search for `appmod`, and make sure **appmod-mcp-server** is ticked. If it says **Stopped**, click **Start**. Then click **Apply**.

3. **Ask for the upgrade.** Start a new chat, and choose the **modernize-java-upgrade** agent. Under the chat box, make sure it says **Copilot**, not **Local**. Then send:

   > Upgrade this app to Java 25 and the latest Spring Boot 4. Use the JDK 25 that's installed, move the tests to JUnit Jupiter without changing what they check, and upgrade any library with known CVEs.

   Copilot writes a plan, `plan.md`, in `.github/modernize/java-upgrade/`, then upgrades in steps. It can take about 8 minutes. At the end, it writes `summary.md` next to the plan.

4. **Check the libraries yourself.** Click **Keep All**, press **Ctrl+Shift+O** to load the Maven changes, and run **Vulnerable Dependencies** again. Copilot's CVE check only looks at the libraries in `pom.xml`, so it can miss the ones that Spring Boot brings in. If any are left, send, with the libraries that your check finds:

   > IntelliJ's Vulnerable Dependencies check still finds 5 vulnerable libraries that Spring Boot brings in: logback-classic, jackson-databind, json-smart, asm and android-json. Override each one with a version that fixes its CVEs, or exclude it if there's no fix, and keep the tests passing.

5. **Run the tests and the store.** Run `.\mvnw.cmd verify` (on macOS and Linux, `./mvnw verify`): both tests run and pass. Then, in `StoreApplication.java`, click the green **Run** arrow next to `main`. <http://localhost:8080> shows the six products, and Spring Boot 4.

<details>
<summary>Hints</summary>

The upgrade usually changes these files:

| File | What changes |
|---|---|
| `pom.xml` | Java 25, Spring Boot 4, newer libraries, and no JUnit Vintage engine. |
| `Catalog.java`, `StoreController.java` | The `javax` imports become `jakarta`. |
| `CatalogTest.java`, `StoreApplicationTests.java` | The JUnit 4 imports become JUnit Jupiter, `@RunWith(SpringRunner.class)` goes, and `AutoConfigureMockMvc` moves to a new package. The checks stay the same. |

If Copilot tries to download another JDK, and the download fails, tell it to use JDK 25 for every step.

If the build says `Tests run: 0`, the tests didn't move to JUnit Jupiter. Ask Copilot to fix that, without removing any test.

Your libraries, versions and CVE counts can differ from the solution's: the vulnerability data changes.

If Copilot can't run Maven, and the output says `Access is denied`, the sandbox from lesson 3 is on. In Settings → GitHub Copilot → Sandbox, turn off **Enable Sandbox by default for new Copilot sessions**, and start a new chat.

</details>

## Done when

- [ ] `pom.xml` uses Java 25 and Spring Boot 4.
- [ ] Both tests use JUnit Jupiter, still check what they checked before, and pass: `.\mvnw.cmd verify`.
- [ ] Code → Analyze Code → **Vulnerable Dependencies** finds no vulnerable libraries.
- [ ] After `.\mvnw.cmd spring-boot:run`, http://localhost:8080 shows the six products, and Spring Boot 4.

Stuck? Read the [solution](SOLUTION.md).
