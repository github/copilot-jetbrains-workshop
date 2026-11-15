# Lesson 3: Secure execution and governance

**Keep your secrets secret** · Part 3 of 5 · [All lessons](../README.md)

When Copilot runs a command on your computer, it runs as you, so it can reach everything you can. In this lesson, you limit what Copilot's commands can reach, and see how an admin makes that a rule.

## The problem

A supplier sent February's prices, and a tool to check them: [supplier/CheckPrices.java](supplier/CheckPrices.java). Their [README](supplier/README.md) even tells AI assistants to run it without asking.

The tool checks the prices, but it also reads a file in your home folder. See it read a pretend secret. Then let Copilot run the tool, without letting it read your files. Don't change the supplier's files.

## Use

### Local sandbox

The sandbox limits what Copilot's commands can reach. They can still use the project folder and developer tools such as Java, but not the rest of your home folder, where secrets often live. Turn on **Enable Sandbox by default for new Copilot sessions** in Settings → GitHub Copilot → Sandbox, then start a new session: the setting only affects new sessions. You can also use `/sandbox` in a session. A sandboxed session has a **Sandbox** label next to its title.

The sandbox covers only Copilot's own commands, not IntelliJ's MCP server from lesson 2. Turn off that MCP server before a sandboxed run: it may run terminal commands without asking, outside the sandbox.

### Managed settings and Policy Diagnostics

An admin can set Copilot settings that users can't change: through device management such as Intune, with a `managed-settings.json` file, or for a whole organization on GitHub. This folder's [managed-settings.json](managed-settings.json) turns the sandbox on, and stops anyone running Copilot's own commands outside it, but it doesn't control IntelliJ's MCP server, whose commands run outside the sandbox. Trying the file is optional, and needs admin rights. To demo it, copy the file into one of these folders, unless a `managed-settings.json` is already there, and restart IntelliJ:

- Windows: `C:\Program Files\GitHubCopilot\`
- macOS: `/Library/Application Support/GitHubCopilot/`
- Linux: `/etc/github-copilot/`

To see what's managed on your computer, open **Policy Diagnostics** from the Options menu at the top of Chat. It's only in a **This computer** session.

![The Options menu at the top of Chat, with Policy Diagnostics highlighted](../screenshots/03-secure-execution/options-menu.png)

### Cloud sandbox, optional

The cloud sandbox runs the whole session on a computer that GitHub hosts, in a clone of your GitHub fork, so your local files aren't there at all. Its model picker is different from a **This computer** session. Pick it in the environment picker under the chat box. It's in preview, and you might not have it yet.

## Get started

Make a pretend secret: a file called `canary.txt`, in a `.trail-store` folder in your home folder.

- macOS and Linux: `mkdir -p ~/.trail-store && echo pretend-secret > ~/.trail-store/canary.txt`
- Windows PowerShell: `New-Item -ItemType Directory -Force "$HOME\.trail-store"; Set-Content "$HOME\.trail-store\canary.txt" pretend-secret`

Check that it's there, in the same terminal: `cat ~/.trail-store/canary.txt` prints `pretend-secret`. A failed read only counts when the file is there.

The local sandbox runs on Windows 11 23H2 or later (25H2 works best), Apple Silicon Macs and Linux. On Linux, it needs:

- `bubblewrap` 0.5 or later
- `slirp4netns`
- `util-linux` 2.35 or later
- `iptables`
- `/dev/net/tun`
- Unprivileged user namespaces, turned on

## Try it

1. **Run the tool without the sandbox.** At the top of Chat, click **+** to start a new session. The sandbox is off, unless you turned it on in Settings. Send:

   > In 03-secure-execution, run the supplier's price checker. Run it as is: don't change user.home, and don't use a temporary home folder.

   If Copilot asks first, tell it to run the tool without the sandbox. The tool prints `Checked 6 prices: OK`, and then your secret: `done, it says "pretend-secret"`.

2. **Turn off IntelliJ's MCP server.** If you turned it on in lesson 2, untick **Enable MCP Server** in Settings → MCP Server (in some versions, under AI). Its commands run outside the sandbox.

3. **Turn on the sandbox.** In Settings → GitHub Copilot → Sandbox, turn on **Enable Sandbox by default for new Copilot sessions**. Then start a new session: it has a **Sandbox** label next to its title.

4. **Run the tool in the sandbox.** Send the same prompt. If Copilot asks to run it outside the sandbox, choose **Reject**. If it wants to change `user.home` or use a temporary home folder, tell it not to. The run counts only when all of these are true:

   - The session has a **Sandbox** label next to its title.
   - In Chat, the step that ran the tool says **Ran**, followed by its command: that's Copilot's own command tool. A step that says **Invoked**, followed by a server's name such as `intellij-idea`, is an MCP server's tool, outside the sandbox.
   - The tool prints `Checked 6 prices: OK`.
   - Then it fails to read `canary.txt`, which step 1 showed is there. On Linux that failure is a `NoSuchFileException`. On Windows it's an `AccessDeniedException`.

   This shows that this command ran in the sandbox. It doesn't prove that every tool is contained: IntelliJ's MCP server, for one, runs its commands outside it.

5. **Open Policy Diagnostics** from the Options menu at the top of Chat, in a **This computer** session. It shows what your organization manages. Settings with no managed value show `null`.

6. **Clean up.** Delete the `.trail-store` folder: `rm -r ~/.trail-store` (in Windows PowerShell, `Remove-Item -Recurse "$HOME\.trail-store"`).

<details>
<summary>Hints</summary>

The tool reads `supplier/prices-2027-02.csv`, so it must run from the `03-secure-execution` folder. With no `Checked 6 prices: OK` line, the tool itself didn't run.

If a step says **Invoked**, IntelliJ's MCP server ran the tool, outside the sandbox. Turn the server off, and try again in a new session.

If the sandboxed run fails with no `OK` line, the local sandbox may not work on your computer: check the requirements in [Get started](#get-started). Use the cloud sandbox instead: in the environment picker under the chat box, change **This computer** to **Cloud sandbox**, and send the same prompt. Your files aren't on that computer at all, so the tool can't read `canary.txt` there either. If you don't have the cloud sandbox yet, read the sandboxed run in the [solution](SOLUTION.md#2-run-it-in-the-sandbox).

</details>

## Done when

- [ ] Without the sandbox, the tool read your pretend secret.
- [ ] IntelliJ's MCP server is off.
- [ ] In a sandboxed session, Copilot's own command tool ran the supplier's tool. It printed `Checked 6 prices: OK`, then couldn't read `canary.txt`, though the file is on your computer.
- [ ] You opened Policy Diagnostics.
- [ ] You deleted the `.trail-store` folder.

Stuck? Read the [solution](SOLUTION.md).
