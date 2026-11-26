# GitHub Copilot in IntelliJ IDEA: workshop

Each lesson has a simple challenge to solve in IntelliJ IDEA with GitHub Copilot, and a separate solution that shows how, with screenshots.

📊 Slides: [GitHub Copilot in IntelliJ IDEA workshop deck](slides/GitHub-Copilot-IntelliJ-Workshop.pptx)

<p align="center"><img src="screenshots/landing.png" alt="GitHub Copilot in IntelliJ IDEA workshop: Copilot Chat adding search to Trail Store, beside the store showing only Ridge Hiking Boots" width="800"></p>

| Lesson | Topic | Features | Challenge | Solution | Time |
|---|---|---|---|---|---|
| 1 | Fundamentals | Parallel sessions and worktrees, next edit suggestions | [Two features at once](01-fundamentals/README.md) | [Solution](01-fundamentals/SOLUTION.md) | About 20 min |
| 2 | Tooling | Customization panel, built-in MCP server, browser use | [Free jackets](02-tooling/README.md) | [Solution](02-tooling/SOLUTION.md) | About 30 min |
| 3 | Secure execution and governance | Local sandbox, managed settings and Policy Diagnostics, cloud sandbox | [Keep your secrets secret](03-secure-execution/README.md) | [Solution](03-secure-execution/SOLUTION.md) | About 15 min |
| 4 | AI FinOps | Copilot Fusion, agent debug logs, OpenTelemetry | [What does a task cost?](04-ai-finops/README.md) | [Solution](04-ai-finops/SOLUTION.md) | About 20 min |
| 5 | App modernization | GitHub Copilot modernization plugin, vulnerable dependencies check | [An old app, a new home](05-app-modernization/README.md) | [Solution](05-app-modernization/SOLUTION.md) | About 30 min |

The whole workshop takes about 2 hours, and much of that is waiting while Copilot works. The times come from test runs of every lesson. Lesson 5's upgrade alone takes 8 to 15 minutes.

## What you need

- IntelliJ IDEA 2026.3, with the GitHub Copilot plugin 1.21.1 or later
- Java 25
- Git, with your name and email set. When you apply a session's changes, Copilot makes a checkpoint commit, which fails without them:

  ```bash
  git config --global user.name "Your Name"
  git config --global user.email "you@example.com"
  ```

If you don't have the GitHub Copilot plugin, install it from Settings → Plugins → Marketplace. If yours is older, update it in Settings → Plugins. Then restart IntelliJ.

Lessons 3 and 5 need a little more:

- Windows 11 23H2 or later, an Apple Silicon Mac or Linux, for the local sandbox in lesson 3. On Linux, it also needs a few packages and settings: see lesson 3's [Get started](03-secure-execution/README.md#get-started).
- The **GitHub Copilot modernization** plugin 1.14.1 or later, for lesson 5: its README explains how to install it.

These are optional, and only for one part of a lesson:

- Node.js 22 or later, and Chrome or Edge with the GitHub Copilot browser extension, for browser use in lesson 2
- Docker, installed and running, to see traces in lesson 4

## Get started

Fork this repo, clone your fork, and open the folder in IntelliJ IDEA. Then start with [lesson 1](01-fundamentals/README.md).

Each lesson's folder has the challenge in `README.md` and the solution in `SOLUTION.md`. [.github/copilot-instructions.md](.github/copilot-instructions.md) asks Copilot to stay inside the lesson's folder: it's a request, not a restriction. Lessons 1, 2, 4 and 5 are copies of Trail Store, a small Spring Boot application. In lessons 1, 2 and 4, failing tests show what to fix. Lesson 5's copy is an old one, for Copilot to upgrade. The lessons don't depend on each other.

Browser use, the cloud sandbox and Copilot Fusion are still rolling out, so you might not have them yet. You can still finish every lesson without them.

### How to work through a lesson

Read **The problem** and **Core concepts**, and run the tests in **Get started**. Then follow **Try it**. In lessons 1, 2 and 4, you write the prompts yourself: a step says what to ask for, and **A prompt that works**, under it, has one you can copy. In lessons 3 and 5, the prompts are ready to copy into Chat. If you get stuck, open **Hints**, and then the solution. Each solution starts with the core concepts and the plan, then walks through every step, with screenshots.

Run each lesson's commands from its own folder. Every store uses port 8080, so stop one store before you start another.

If you don't see **+**, **Customizations** or the Options menu at the top of Chat, hover over the top of Chat.

If you click **+** and Chat still shows the list of sessions, the new session may have opened as a **Copilot Chat** tab in the editor area: hide the Chat tool window, by clicking its icon in the sidebar, to see it.

A new session keeps the agent you used last. Unless a lesson says otherwise, choose **Agent** in the **Agent** selector, in the chat box: lesson 2's **bug-hunter** works only in `02-tooling`.

Copilot asks before it runs a command, such as `git status` or `./mvnw`, or reads a file it treats as sensitive. Check what it wants to do, and click **Allow Once**. To stop it asking again in that session, choose **Allow all commands in this Session** from the arrow next to **Allow Once**.

In lesson 3, you turn on the sandbox for new sessions. Turn it off again when you finish that lesson: in the sandbox, Copilot can't run the other lessons' Maven builds.

## Contributing

If a step doesn't work, or a screenshot is out of date, [open an issue](https://github.com/github/copilot-jetbrains-workshop/issues/new/choose). To send a fix, read [CONTRIBUTING.md](CONTRIBUTING.md).

This project is released with a [Contributor Code of Conduct](CODE_OF_CONDUCT.md). By participating in this project you agree to abide by its terms.

## License

This project is licensed under the terms of the MIT open source license. Please refer to [LICENSE](LICENSE) for the full terms.

## Maintainers

GitHub's Java DevRel team maintains this workshop. See [CODEOWNERS](.github/CODEOWNERS).

## Support

The workshop is maintained by GitHub staff and the community. For where to get help, see [SUPPORT.md](SUPPORT.md).
