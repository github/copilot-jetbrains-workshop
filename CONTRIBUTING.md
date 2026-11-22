# Contributing

[fork]: https://github.com/github/copilot-jetbrains-workshop/fork
[pr]: https://github.com/github/copilot-jetbrains-workshop/compare

Hi there! We're thrilled that you'd like to contribute to this workshop. Your help is essential for keeping it great.

Contributions to this project are [released](https://docs.github.com/site-policy/github-terms/github-terms-of-service#6-contributions-under-repository-license) to the public under the [project's open source license](LICENSE).

Please note that this project is released with a [Contributor Code of Conduct](CODE_OF_CONDUCT.md). By participating in this project you agree to abide by its terms.

## Reporting a problem

If a step doesn't work as described, a screenshot is out of date, or something is unclear, [open an issue](https://github.com/github/copilot-jetbrains-workshop/issues/new/choose). Say which lesson and step, and which versions of IntelliJ IDEA and the GitHub Copilot plugin you use.

For problems with the plugins themselves, see [SUPPORT.md](SUPPORT.md).

## Submitting a pull request

1. [Fork][fork] and clone the repository
1. Create a new branch: `git checkout -b my-branch-name`
1. Make your change
1. If you changed a lesson's Maven project, make sure it still builds: from the lesson's folder, run `./mvnw test-compile`
1. Push to your fork and [submit a pull request][pr]
1. Pat yourself on the back and wait for your pull request to be reviewed and merged.

Here are a few things you can do that will increase the likelihood of your pull request being accepted:

- Keep each pull request to one lesson: its folder, and its screenshots in `screenshots/`.
- Keep the challenge and the solution in step: if you change a lesson's `README.md`, check its `SOLUTION.md` too.
- Leave the challenge unsolved: in lessons 1, 2 and 4, the failing tests show attendees what to fix. The **Build lessons** check fails unless exactly those tests fail.
- Remove personal information, such as names, user names and email addresses, from screenshots.
- Keep your change as focused as possible. If there are multiple changes you would like to make that are not dependent upon each other, consider submitting them as separate pull requests.
- Write a clear commit message: a short summary line that says what the change does, then a blank line, then why, if it isn't obvious.

## Resources

- [How to Contribute to Open Source](https://opensource.guide/how-to-contribute/)
- [About pull requests](https://docs.github.com/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests)
- [GitHub Docs](https://docs.github.com)
