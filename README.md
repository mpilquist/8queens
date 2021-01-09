The [Eight Queens problem](https://en.wikipedia.org/wiki/Eight_queens_puzzle) asks how 8 queens can be placed on a chess board such that no queen can capture any other queen. It's a classic problem in computer science, popularized by Dijkstra as an example of backtracking.

This repository provides a starting point for folks that want to try solving the eight queens problem using the Scala programming language. The intended audience are folks who are new to Scala and looking for an exercise. The main branch contains the skeleton of the problem and the necessary build setup to get moving right away.

To get started:

### Setup build environment

You'll need a Java development kit installed as well as the SBT build tool. If you don't have these tools, we can get them via Couriser. First, install Coursier by choosing an installation method for your operating system on this page: https://get-coursier.io/docs/cli-installation. Then run `cs setup`.

You'll also likely want and editor that's aware of Scala syntax. I recommend [VSCode](https://code.visualstudio.com) with the [Metals](https://scalameta.org/metals/docs/editors/vscode.html) extension.

### Clone this repository

```
> git clone https://github.com/mpilquist/8queens
> cd 8queens
```


### Launch SBT

From the directory in which you cloned this repository, let's launch our build tool:

```
> sbt
[info] welcome to sbt 1.4.6 (AdoptOpenJDK Java 11.0.9)
[info] loading settings for project root-8queens-build from plugins.sbt ...
[info] loading project definition from /Users/mpilquist/Development/oss/8queens/project
[info] loading settings for project root-8queens from build.sbt ...
[info] set current project to 8queens (in build file:/Users/mpilquist/Development/oss/8queens/)
[info] sbt server started at local:///Users/mpilquist/.sbt/1.0/server/5f0aa042a9c88a92742e/sock
[info] started sbt server
sbt:8queens>
```

If you just installed SBT, you'll see some additional output as it downloads various dependencies, but eventually you'll be placed at the SBT prompt. From there, you can run various commands. For example, the `compile` command will compile the source code:

```
sbt:8queens> compile
[success] Total time: 2 s, completed Jan 9, 2021, 10:38:33 AM
sbt:8queens>
```

The `run` command will run the program defined in the source code, which prints out all solutions to the eight queens problem for an 8x8 chess board:

```
sbt:8queens> run
[info] running queens.EightQueens
[error] (run-main-0) scala.NotImplementedError: an implementation is missing
[error] scala.NotImplementedError: an implementation is missing
[error] 	at scala.Predef$.$qmark$qmark$qmark(Predef.scala:345)
[error] 	at queens.Board.solutions(Board.scala:41)
[error] 	at queens.EightQueens$.<clinit>(EightQueens.scala:5)
[error] 	at queens.EightQueens.main(EightQueens.scala)
[error] 	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
[error] 	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
[error] 	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
[error] 	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
[error] stack trace is suppressed; run last Compile / bgRun for the full output
[error] Nonzero exit code: 1
[error] (Compile / run) Nonzero exit code: 1
[error] Total time: 1 s, completed Jan 9, 2021, 10:41:44 AM
```

This fails when you first check out this project. Your goal is to fill in the missing implementations so that this program prints the various solutions. To assist in this goal, there's a unit test suite provided as well.

The `test` command will run the test suite:

```
sbt:8queens> test
queens.BoardSuite:
==> X queens.BoardSuite.valid - empty 8x8 board is valid  0.039s scala.NotImplementedError: an implementation is missing
    at munit.FunSuite.assert(FunSuite.scala:11)
    at queens.BoardSuite.$init$$$anonfun$1(BoardSuite.scala:22)
==> X queens.BoardSuite.valid - column conflicts  0.002s scala.NotImplementedError: an implementation is missing
    at munit.FunSuite.assert(FunSuite.scala:11)
    at queens.BoardSuite.$init$$$anonfun$2(BoardSuite.scala:22)
==> X queens.BoardSuite.valid - row conflicts  0.001s scala.NotImplementedError: an implementation is missing
    at munit.FunSuite.assert(FunSuite.scala:11)
    at queens.BoardSuite.$init$$$anonfun$3(BoardSuite.scala:22)
==> X queens.BoardSuite.valid - diagonal conflicts  0.001s scala.NotImplementedError: an implementation is missing
    at munit.FunSuite.assert(FunSuite.scala:11)
    at queens.BoardSuite.$init$$$anonfun$4(BoardSuite.scala:22)
==> X queens.BoardSuite.solutions - empty 8x8 - all solutions are valid  0.001s scala.NotImplementedError: an implementation is missing
    at scala.Predef$.$qmark$qmark$qmark(Predef.scala:345)
    at queens.Board.solutions(Board.scala:41)
    at queens.BoardSuite.$init$$$anonfun$5(BoardSuite.scala:27)
==> X queens.BoardSuite.solutions - empty 8x8 - all queens are placed  0.001s scala.NotImplementedError: an implementation is missing
    at scala.Predef$.$qmark$qmark$qmark(Predef.scala:345)
    at queens.Board.solutions(Board.scala:41)
    at queens.BoardSuite.$init$$$anonfun$6(BoardSuite.scala:32)
==> X queens.BoardSuite.solutions - empty 8x8 - finds all solutions  0.001s scala.NotImplementedError: an implementation is missing
    at scala.Predef$.$qmark$qmark$qmark(Predef.scala:345)
    at queens.Board.solutions(Board.scala:41)
    at queens.BoardSuite.$init$$$anonfun$7(BoardSuite.scala:37)
==> X queens.BoardSuite.solutions - 8x8 with A1 - finds all solutions  0.0s scala.NotImplementedError: an implementation is missing
    at scala.Predef$.$qmark$qmark$qmark(Predef.scala:345)
    at queens.Board.solutions(Board.scala:41)
    at queens.BoardSuite.$init$$$anonfun$8(BoardSuite.scala:46)
[error] Failed: Total 8, Failed 8, Errors 0, Passed 0
[error] Failed tests:
[error] 	queens.BoardSuite
[error] (Test / test) sbt.TestsFailedException: Tests unsuccessful
[error] Total time: 1 s, completed Jan 9, 2021, 10:39:15 AM
```

As indicated by the output, there's a number of unit tests already setup in this project. Your goal is to get them all passing. They are designed such that each successive test builds on the previous, so focus on getting the first test passing before moving on to the second.

### Code Layout

There are a few source files:
 - `src/main/scala/queens/Board.scala` - defines the main `Board` type
 - `src/main/scala/queens/EightQueens.scala` - defines the program which initializes an 8x8 board and prints out all solutions
 - `src/test/scala/queens/BoardSuite.scala` - defines the unit tests for the `Board` type

You only need to edit `Board.scala`, replacing each occurrence of `???` with an implementation that causes the tests to pass.

### Solution

The `solution` branch contains a solution that passes all of the tests. Feel free to browse it if you get stuck, but try not to look!

