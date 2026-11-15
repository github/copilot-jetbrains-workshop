# Lesson 1: Fundamentals

**Two features at once** · Part 1 of 5 · [All lessons](../README.md)

Copilot can work on more than one task at a time. In this lesson, you run two Copilot sessions side by side, each in its own copy of the code. Then Copilot helps with your own typing, by predicting your next edit.

## The problem

Trail Store needs two small features:

- **Search.** `/?q=boots` shows only the products whose name contains "boots", ignoring case. Without `q`, the page still shows all six products. You don't need a search box.
- **Low stock.** A product with 1 to 5 left says "Only 4 left" instead of "4 in stock". The other products don't change: "12 in stock", or "Out of stock".

Build both at the same time, with two Copilot sessions that don't get in each other's way. Then make one change yourself:

- **Categories.** Each product shows its category under its name, as `<p class="category">Jackets</p>`. The jackets are in Jackets, the boots in Boots, the pack in Packs, and the tent in Tents.

Don't change the tests.

## Use

### Parallel sessions and worktrees

A session is one conversation with Copilot, about one task. Click **+** at the top of Chat to start another one. Sessions run at the same time.

Two sessions in the same folder can trip over each other's changes. A git worktree solves that: it's a second folder with its own copy of the code, on its own branch, in the same repo. Pick **Worktree** in the row under the chat box, and the session gets its own worktree, starting from your last commit. When it's done, **Apply**, above the chat box, copies its changes into your project.

![The Workspace menu under the chat box, with Worktree highlighted](../screenshots/01-fundamentals/worktree.png)

### Next edit suggestions

Completions finish the line you're typing. Next edit suggestions go further: after you make an edit, Copilot suggests the edit that follows from it, even elsewhere in the file. If the suggestion is where you're typing, **Tab** accepts it. If it's somewhere else, the first **Tab** jumps to it, and the second accepts it. Check it's on in Settings → GitHub Copilot → Completions.

## Get started

Open this repo in IntelliJ IDEA with the GitHub Copilot plugin, and run the tests:

```bash
cd 01-fundamentals
./mvnw -B -ntp verify
```

On Windows, use `.\mvnw.cmd -B -ntp verify`. `SearchTests`, `LowStockTests` and `CategoryTests` fail.

## Try it

1. **Start the search session.** At the top of Chat, click **+**. In the row under the chat box, click **Workspace**, choose **Worktree**, and send:

   > In 01-fundamentals, make SearchTests pass. Change only what search needs, and run SearchTests to check.

   If an **Uncommitted Changes** card appears, click the arrow next to **Copy Changes**, and choose **Skip Changes**: the worktree starts from your last commit.

2. **Start the low stock session.** Don't wait for the first one to finish. Click **+** again, choose **Worktree**, and send:

   > In 01-fundamentals, make LowStockTests pass. Change only what low stock needs, and run LowStockTests to check.

   If the **Uncommitted Changes** card appears, skip the changes again. To switch between the two sessions, click **Show Sessions Side Bar** at the top of Chat.

3. **Apply both.** When a session finishes, the files it changed are listed above the chat box. Click the arrow before the list's header, such as **1 File Changed**, to open it. Click a file to check its diff, then click **Apply**. There's no confirmation: the list just closes. Do the same in the other session. Now only `CategoryTests` fails.

4. **Add the categories yourself.** In `Product.java`, add `String category` after `name`. Then:

   - In `Catalog.java`, type the first product's category, `"Jackets",`, after its name. Copilot suggests the other five categories: press **Tab** to accept them.
   - In `index.html`, click at the end of the `<h2>` line, and press **Enter**. Copilot suggests the category line: press **Tab** to accept it.

   If no suggestion appears, type the change yourself.

5. **Check.** Run the tests again: they all pass. To see the page, start the store with `./mvnw spring-boot:run` (on Windows, `.\mvnw.cmd spring-boot:run`), and open <http://localhost:8080/?q=boots>.

<details>
<summary>Hints</summary>

The Java files are in `src/main/java/com/contoso/store`. The page is `src/main/resources/templates/index.html`.

| Task | File | Hint |
|---|---|---|
| Search | `StoreController.java` | Add an optional `q` request parameter that defaults to `""`. Keep only the products whose name contains `q`, comparing both in lower case. |
| Low stock | `index.html` | Split the "in stock" line in two: one for more than 5 left, and one for 1 to 5 left. |
| Categories | `Catalog.java` | Once `Product` has a category, `Catalog.java` doesn't compile until each of the six products has one, between its name and its price. |
| Categories | `index.html` | Under the `<h2>`, add `<p class="category" th:text="${product.category()}">Jackets</p>`. |

To run one test class, click the green run icon next to it.

</details>

## Done when

- [ ] All the tests pass.
- [ ] Search and low stock each came from their own worktree session.
- [ ] You typed the categories yourself, with next edit suggestions when Copilot offers them.

Stuck? Read the [solution](SOLUTION.md).
