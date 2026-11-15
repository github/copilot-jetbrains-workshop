# Lesson 2: Tooling

**Free jackets** · Part 2 of 5 · [All lessons](../README.md)

Copilot works better when it knows your project and can use your tools. In this lesson, you customize Copilot, and connect it to IntelliJ's own tools.

## The problem

It's sale week: every jacket is 20% off. But the store sells the jackets for $0.

Find the bug and fix it. Copilot does the work with IntelliJ's own test runner, debugger and refactorings. The jackets should cost $79, $111 and $119: 20% off $99, $139 and $149, rounded down to whole dollars. Don't change the tests.

Also set up Copilot for this workspace, and rename one method:

- Install the **java-development** plugin.
- Create a **bug-hunter** custom agent and a **run-the-store** skill, for the workspace.
- Rename `all()` in `Catalog.java` to `products()`, with IntelliJ's rename refactoring. If you do the rename yourself with **Shift+F6**, untick **Search in comments and strings** and **Search for text occurrences**, so the change stays in this lesson's folder.

## Use

### Customization panel

**Customizations**, at the top of Chat, is where you shape what Copilot knows and can do:

- A **plugin** is a ready-made bundle, such as prompts, instructions and agents, from a marketplace.
- A **custom agent** is Copilot with your own instructions for one kind of job. You pick it in the **Agent** selector in the chat box, after you restart IntelliJ.
- A **skill** teaches Copilot one task, such as how to run the store. Copilot reads it when the task comes up.

The agents and skills you create for the workspace are files in `.github/`, so everyone who clones the repo gets them.

### Built-in MCP server

MCP, the Model Context Protocol, is an open standard that lets AI use tools from other programs. IntelliJ has its own MCP server, so Copilot can use IntelliJ's test runner, debugger and rename refactoring, not just the terminal. Turn it on in Settings → MCP Server (in some versions, under AI), or search Settings for MCP Server. Its tools run outside the sandbox you'll meet in lesson 3, so turn it off when you finish this lesson.

![Customizations, MCP Servers, with IntelliJ IDEA MCP Server running](../screenshots/02-tooling/mcp-servers.png)

### Browser use, optional

Copilot opens pages in your own browser and reads them, so it can check a change in the running app. It's a preview. Turn it on in Settings → GitHub Copilot → Chat → Agent. It needs Node.js 22 or later, and Chrome or Edge with the GitHub Copilot browser extension.

## Get started

Copilot uses IntelliJ's test runner and debugger in this lesson, so set up the project in IntelliJ first:

- If the **Maven** tool window is empty, right-click the `pom.xml` at the top of the repo, and choose **Add as Maven Project**.
- In File → Project Structure → Project, set **SDK** to JDK 25.

Then run the tests:

```bash
cd 02-tooling
./mvnw -B -ntp verify
```

On Windows, use `.\mvnw.cmd -B -ntp verify`. `SaleTests` fails: the jackets should cost $79, $111 and $119.

## Try it

1. **Turn on IntelliJ's MCP server.** In Settings → MCP Server (in some versions, under AI), select **Enable MCP Server**, and click **OK**. In **Customizations**, at the top of Chat, **MCP Servers** lists **IntelliJ IDEA MCP Server** as **Running**.

2. **Fill the Customization panel.** In **Customizations**:

   - Under **Plugins**, click **Browse Marketplace**, search for `java-development`, and click **Install**. If the search fails, wait a moment and try again.
   - Click **Agent**, click the arrow next to **Generate Agent**, and choose **New Agent (Workspace)**. Enter `bug-hunter`, and click **Create**. The new file opens in an editor: replace all its text with the agent in **Hints**, and click **Save**, at the top right of the editor.
   - Click **Skills**, click the arrow next to **Generate Skill**, and choose **New Skill (Workspace)**. Enter `run-the-store`, and click **Create**. Replace all its text with the skill in **Hints**, and click **Save**.

   Then restart IntelliJ, so that the **Agent** selector lists **bug-hunter**.

3. **Find the bug.** Start a new session. If the row under the chat box says **Worktree**, change it to **Workspace**. Pick **bug-hunter** in the **Agent** selector, and send:

   > In 02-tooling, jackets should be 20% off, but the store sells them for $0. SaleTests fails. Find out why and fix it.

   Copilot runs `SaleTests`, debugs it in IntelliJ's debugger, and reads the values at a breakpoint. Check each command it asks to run, and confirm it. When it's done, click **Keep All**, above the chat box, to keep the fix.

4. **Rename with IntelliJ.** Send:

   > In 02-tooling, rename the all() method in Catalog.java to products(), with IntelliJ's rename refactoring. Rename code only, without searching comments and text. Change only files in 02-tooling.

   If Copilot stops because some references are outside `02-tooling`, do the rename yourself: in `Catalog.java`, click `all`, press **Shift+F6**, type `products`, untick **Search in comments and strings** and **Search for text occurrences**, and press **Enter**.

   The rename should change only `Catalog.java` and `StoreController.java`, but IntelliJ can also change the Java code in another lesson's `SOLUTION.md`. Open the **Commit** tool window: if a `SOLUTION.md` is listed, right-click it, choose **Rollback**, and confirm.

5. **Check the page (optional).** In `02-tooling`, start the store with `./mvnw spring-boot:run` (on Windows, `.\mvnw.cmd spring-boot:run`), and open <http://localhost:8080>. The jackets cost $79, $111 and $119. Stop the store with **Ctrl+C**.

6. **Turn off IntelliJ's MCP server.** In Settings → MCP Server, untick **Enable MCP Server**. Its tools run outside the sandbox in lesson 3.

<details>
<summary>Hints</summary>

The agent, `.github/agents/bug-hunter.agent.md`:

```markdown
---
description: Fixes bugs with IntelliJ's test runner and debugger.
---
Work only in 02-tooling, and keep the tests unchanged.
Fix bugs with evidence:
1. Run the failing test with IntelliJ's test runner.
2. Debug the test in IntelliJ, and read the variables at a breakpoint.
3. Explain the cause in one sentence, make the smallest fix, and run the test again.
```

The skill, `.github/skills/run-the-store/SKILL.md`:

```markdown
---
name: run-the-store
description: Runs the tests and starts the store for one lesson of this workshop.
---
Each lesson is its own app, in its own folder, like 02-tooling.
Run the tests with ./mvnw -B -ntp verify (on Windows, .\mvnw.cmd -B -ntp verify).
Start the store with ./mvnw spring-boot:run, and open http://localhost:8080.
```

The bug is in `withSalePrice()`, in `Catalog.java`. What is `(100 - JACKET_SALE_PERCENT) / 100`, when Java divides two whole numbers?

If Copilot doesn't debug, set a breakpoint yourself: click in the gutter next to the `salePrice` line. Then, in `SaleTests.java`, click the green run icon next to the class, and choose **Debug**.

</details>

## Done when

- [ ] All the tests pass.
- [ ] Copilot found the bug in IntelliJ's debugger.
- [ ] `all()` in `Catalog.java` is renamed to `products()`, by IntelliJ's rename refactoring, and no `SOLUTION.md` changed.
- [ ] Customizations lists your plugin, agent and skill.
- [ ] IntelliJ's MCP server is off.

Stuck? Read the [solution](SOLUTION.md).
