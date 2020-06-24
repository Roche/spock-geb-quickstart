# Contributing

When contributing to this repository, please first discuss the change you wish to make via issue,
email, or any other method with the owners of this repository before making a change. 

Please note we have a code of conduct, please follow it in all your interactions with the project.

 - [Issues and Bugs](#issue)
 - [Feature Requests](#feature)
 - [Submission Guidelines](#submit)
 - [Coding Rules](#rules)
 - [Git Commit Guidelines](#commit)

## <a name="issue"></a> Issues and Bugs
If you find a bug in the source code or a mistake in the documentation, you can help us by [submitting a ticket](https://github.com/Roche/spock-geb-quickstart/issues).
**Even better**, you can submit a Pull Request to our project.

**Please see the Submission Guidelines below**.

## <a name="feature"></a> Feature Requests
You can request a new feature by submitting a ticket to our [issue list](https://github.com/Roche/spock-geb-quickstart/issues).
If you would like to implement a new feature then open up a ticket which clearly states that it is a feature request in the title. 
Explain your change in the description, and you can propose a Pull Request straight away.

## <a name="submit"></a> Submission Guidelines

### [Submitting an Issue](https://opensource.guide/how-to-contribute/#opening-an-issue)
Before you submit your issue search the [archive](https://github.com/Roche/spock-geb-quickstart/issues?q=is%3Aissue), 
maybe your question was already answered or is already there in backlog.

If your issue appears to be a bug, and has not been reported, open a new issue. 
Help us to maximize the effort we can spend fixing issues and adding new
features, by not reporting duplicate issues. 
Providing the following information will increase the chances of your issue being dealt with quickly:

* **Overview of the issue** - if an error is being thrown a stack trace helps
* **Motivation for or Use Case** - explain why this is a bug for you
* **Reproduce the error** - an unambiguous set of steps to reproduce the error. 
* **Related issues** - has a similar issue been reported before?
* **Suggest a Fix** - if you can't fix the bug yourself, perhaps you can point to what might be causing the problem (line of code or commit or general idea)

Issues opened without any of these info may be **closed** without any explanation.

### [Submitting a Pull Request](https://opensource.guide/how-to-contribute/#opening-a-pull-request)
Before you submit your pull request consider the following guidelines:

* Search [Pull Requests](https://github.com/Roche/spock-geb-quickstart/pulls) for an open or closed Pull Request
  that relates to your submission.
* Make your changes in a new git branch. Branch name **must contain the issue key**, e.g. `123` 

     ```shell
     git checkout -b my-branch master
     ```

* Create your patch, **including appropriate test cases**. 
* Follow our [Coding Rules](#rules).
* Ensure that all tests pass

     ```shell
     ./gradlew clean build iT
     ```
     
* Check appropriate [Travis CI build](TODO) to see if tests are passing. 
Travis CI build will pass if there are not errors. If there are any, branch merge will be blocked.

* Commit your changes using a descriptive commit message that follows our
  [commit message conventions](#commit-message-format).

     ```shell
     git commit -a
     ```

  Note: the optional commit `-a` command line option will automatically "add" and "rm" edited files.

* Push your branch to GitHub:

    ```shell
    git push origin my-branch
    ```

* In GitHub, [send a pull request](https://github.com/Roche/spock-geb-quickstart/pulls).
* There will be default reviewers added. Please add other reviewers if you want anyone else to check your Pull Request
* If any changes are suggested then
  * Make the required updates.
  * Re-run tests ensure tests are still passing.
  * Rebase your branch and force push to your GitHub repository (this will update your Pull Request):

    ```shell
    git rebase master -i
    git push -f
    ```

That's it! Thank you for your contribution!

#### Resolving merge conflicts ("This branch has conflicts that must be resolved")

Sometimes your PR will have merge conflicts with the upstream repository's master branch. There are several ways to solve this but if not done correctly this can end up as a true nightmare. So here is one method that works quite well.

* First, fetch the latest information from the master

    ```shell
    git fetch upstream
    ```

* Rebase your branch against the upstream/master

    ```shell
    git rebase upstream/master
    ```

* Git will stop rebasing at the first merge conflict and indicate which file is in conflict. Edit the file, resolve the conflict then

    ```shell
    git add <the file that was in conflict>
    git rebase --continue
    ```

* The rebase will continue up to the next conflict. Repeat the previous step until all files are merged and the rebase ends successfully.
* Re-run tests to ensure tests are still passing.
* Force push to your Bitbucket repository (this will update your Pull Request)

    ```shell
    git push -f
    ```

#### After your pull request is merged

After your pull request is merged, you can safely delete your branch and pull the changes
from the main (upstream) repository:

* Delete the remote branch on Bitbucket either through the Bitbucket web UI or your local shell as follows:

    ```shell
    git push origin --delete my-branch
    ```

* Check out the master branch:

    ```shell
    git checkout master -f
    ```

* Delete the local branch:

    ```shell
    git branch -D my-branch
    ```

* Update your master with the latest upstream version:

    ```shell
    git pull --ff upstream master
    ```
    
## <a name="rules"></a> Coding Rules
To ensure consistency throughout the source code, keep these rules in mind as you are working:

* All features or bug fixes **must be tested** by one or more tests.
* Source files **must be** formatted using Intellij IDEA's code style,

## <a name="commit"></a> Git Commit Guidelines

We have rules over how our git commit messages must be formatted. Please ensure to [squash](https://help.github.com/articles/about-git-rebase/#commands-available-while-rebasing) unnecessary commits so that your commit history is clean.

### <a name="commit-message-format"></a> Commit Message Format
Each commit message consists of a **header** and a **body**.

```
<header>
<BLANK LINE>
<body>
```

Any line of the commit message cannot be longer 100 characters! This allows the message to be easier
to read on Bitbucket as well as in various git tools.

### Header
The Header contains a related issue key and a succinct description of the change:

* use the imperative, present tense: "change" not "changed" nor "changes"
* don't capitalize first letter
* no dot (.) at the end

### Body
If your change is simple, the Body is optional.

Just as in the Header, use the imperative, present tense: "change" not "changed" nor "changes".
The Body should include the motivation for the change and contrast this with previous b

### Example
For example, here is a good commit message:

```
[123] upgrade to Spring Boot 1.1.7

upgrade the Maven and Gradle builds to use the new Spring Boot 1.1.7,
see http://spring.io/blog/2014/09/26/spring-boot-1-1-7-released
```
