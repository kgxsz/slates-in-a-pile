##Notes

- Get `rlwrap` with `brew install rlwrap`.
- To develop: `rlwrap lein figwheel`.
- To connect browser repl, open `localhost:3449`
- To clean up through the repl: `(clean-builds)`
- To restart builds through the repl: `(reset-autobuild)`
- To compile sass: `sass --watch resources/private/sass:resources/public/style`