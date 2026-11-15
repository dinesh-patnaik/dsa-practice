# Repository Rules for Git Operations

1. **Never Commit Automatically**:
   - Never execute `git commit` without explicit instruction from the user.
   - All changes must remain unstaged or staged for review.

2. **Never Push Automatically**:
   - Never execute `git push` without explicit instruction and confirmation.

3. **Approval Protocol**:
   - When asked to commit and push:
     1. Present the list of modified/added files.
     2. Propose the exact commit message.
     3. State the target branch and remote.
     4. Wait for explicit user confirmation before executing `git commit` and `git push`.
