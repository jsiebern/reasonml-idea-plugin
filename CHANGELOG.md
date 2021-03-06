# Changelog

> **Tags:**
> - :boom:       [Breaking Change]
> - :rocket:     [New Feature]
> - :bug:        [Bug Fix]
> - :memo:       [Documentation]
> - :house:      [Internal]
> - :nail_care:  [Polish]

(_Tags are copied from [babel](https://github.com/babel/babel/blob/master/CHANGELOG.md)_)

## 0.48.1

* :bug: Fix a NPE in OCaml (#51)

## 0.48

* :house: Fix folding code
* :house: Better locate js.ml, belt.ml
* :house: Add Pervasives to completion
            
## 0.47.1

* :bug: Fixed 2 bugs (stack and 'editor not disposed' exceptions)
* :house: Fixed recursive type parsing problem with variants

## 0.47

* :rocket: Reason settings to change reformat column width
* :bug: Fix #45 (classes have been deleted)
* :house: Better error extraction
* :house: Reformat on save can be activated (see doc)

## 0.46

* :bug: Fix version 0.45
* :rocket: Better error annotation (includes warnings)
        
## 0.45 

* :nail_care: Bucklescript window has an icon
* :nail_care: File icons updated
* :bug: Goto action use the same path resolution than completion
* :rocket: Display signature for 'val' in completion popup
* :house: Parsers improvement
* :house: Reworked how to get the types from cmi files

## 0.44

* :bug: Fix #40
* :house: Improve completion on expressions

## 0.43

* :bug: Fix 'Type annotations not working with bs namespace' (#35)
* :house: Improve completion on expressions

## 0.42

* :rocket: Add |. operator
* :rocket: goto action on file module is working
* :house: Improve completion on expressions
* :bug: Color settings no more bloked on loading spinner

## 0.41

* :house: Much improved performances for code lens

## 0.40

* :rocket: JSX completion (tag/attribute)
* :rocket: Variant names can be highlighted
* :house: Improved parsers
         
## 0.39

* :house: Better JSX parsing/highlighting
* :rocket: Code lens style is customisable
* :bug: Fix #33
* :rocket: Completion contributor for Ocaml files (wip)

## 0.38

* :house: Better JSX parsing/highlighting
* :house: Fix inferred type annotations
* :house: Improved reason parser

## 0.37

* :bug: Fix #30
* :rocket: add completion when starting typing
* :house: Display types for val/external (completion popup)
* :house: Use mli file when present (pervasives)

## 0.36

* :bug: Fix #29
* :house: Improve parsers
* :rocket: add keyword completion (start expression)
* :rocket: add module completion for open expression

## 0.35

* :bug: Fix an infinite loop in PsiModule
* :house: Improve parsers 

## 0.34

* :bug: Fix a NPE in the folder code when comments are too small
* :bug: [#28](https://github.com/reasonml-editor/reasonml-idea-plugin/issues/28) IntelliJ error "Assertion failed: Too many element types registered. Out of (short) range." **maybe**
* :rocket: [#25](https://github.com/reasonml-editor/reasonml-idea-plugin/issues/25) Support .ml4 files 

## 0.33

* :bug: [#22](https://github.com/reasonml-editor/reasonml-idea-plugin/issues/22) Mishandling of "Comment with Block Comment" action (CTL-SHIFT-/)
* :bug: [#21](https://github.com/reasonml-editor/reasonml-idea-plugin/issues/21) Error message: "startNotify called already" 

## 0.32

* :nail_care: [#20](https://github.com/reasonml-editor/reasonml-idea-plugin/issues/20) Improved notification, add plugin name and link to github 
* :rocket: 'go to' for Open expression (ctrl+click)
* :bug: [#19](https://github.com/reasonml-editor/reasonml-idea-plugin/issues/19) Character constant with an octal character not highlighted correctly 

## 0.31

* :nail_care: val expressions (OCaml) are displayed in the structure view
* :nail_care: Exceptions are displayed in the structure view
* :bug: [#18](https://github.com/reasonml-editor/reasonml-idea-plugin/issues/18) Incorrect handling of characters in parser 
* :house: Improve OCaml parser

## 0.30

* :house: Fix Idea 15 compatibility
* :house: Work done on indexing and referencing (module)

## 0.29

* :house: Redesigned the reason parser

## 0.28

* :rocket: Reformat action is working on ocaml source
* :house: Working on stub indexing and first implementation of completion using static analysis

## 0.27

* :bug: Fixed an IllegalArgumentException in KillableColoredProcessHandler</li>
* :rocket: 
  * Structure view enabled for OCaml files
  * Inferring types using bsc if no merlin found (wip)
* :house: 
  * Using gradle for build
  * Improving reason parser

## 0.26

* :house: Improving parsers

## 0.25

* :rocket: [#14](https://github.com/reasonml-editor/reasonml-idea-plugin/issues/14) Add a very lightweight support of OCaml, to have more syntax coloring and no incorrect parser errors

## 0.24

* :nail_care: [#13](https://github.com/reasonml-editor/reasonml-idea-plugin/issues/13) Add Number and Type argument in color settings

## 0.23

* :house: New default values for bsb and refmt. bsb=> node_modules/bs-platform/bin/bsb.exe, refmt=> node_modules/bs-platform/bin/refmt3.exe

## 0.22

* :rocket: Parse Bsb super errors and use them to annotate source code
