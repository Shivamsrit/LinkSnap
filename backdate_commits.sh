#!/bin/bash

# Create separate commits for existing files using specified dates.
# Run from the root of your Git repository:
# bash backdate_commits.sh

set -e

declare -a DATES=(
  "2026-08-23 09:15:00"
  "2026-08-24 11:30:00"
  "2026-08-25 14:45:00"
  "2026-08-26 10:00:00"
  "2026-08-27 16:20:00"
  "2026-08-28 09:50:00"
  "2026-08-29 13:10:00"
  "2026-08-30 17:00:00"
  "2026-08-31 11:45:00"
  "2026-09-01 10:30:00"
  "2026-09-02 15:00:00"
  "2026-09-03 09:20:00"
  "2026-09-04 09:15:00"
  "2026-09-05 14:45:00"
  "2026-09-06 10:00:00"
  "2026-09-07 11:30:00"
  "2026-09-08 16:20:00"
  "2026-09-09 09:50:00"
  "2026-09-10 13:10:00"
  "2026-09-11 17:00:00"
  "2026-09-12 11:45:00"
  "2026-09-13 09:15:00"
  "2026-09-14 11:30:00"
  "2026-09-15 14:45:00"

)

declare -a FILES=(

  "url-LinkSnap-sb/pom.xml"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/Security/WebSecurityConfig.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/Security/jwt/JwtAuthenticationFilter.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/Security/jwt/JwtUtils.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/controller/AuthController.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/controller/RedirectController.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/controller/UrlMappingController.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/dto/ClickEventDto.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/dto/UrlMappingDto.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/dto/requestdto/LoginRequestDto.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/dto/requestdto/RegisterRequestDto.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/dto/responsedto/UserResponseDto.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/entities/ClickEvent.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/entities/UrlMapping.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/entities/User.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/repositories/ClickEventRepository.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/repositories/UrlMappingRepository.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/repositories/UserRepository.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/services/UrlMappingService.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/services/UserDetailsImpl.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/services/UserDetailsServiceImpl.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/services/UserService.java"
  "url-LinkSnap-sb/src/main/java/com/url/LinkSnap/services/UserServiceImpl.java"
  "url-LinkSnap-sb/src/main/resources/application.properties"
)

declare -a MESSAGES=(

  "build: update Maven dependencies and project configuration"
  "fix: configure Spring Security authentication and authorization"
  "fix: improve JWT authentication filter"
  "fix: update JWT token generation and validation"
  "feat: implement user authentication endpoints"
  "feat: implement short URL redirection endpoint"
  "feat: implement URL mapping management endpoints"
  "feat: add click event response DTO"
  "feat: add URL mapping data transfer object"
  "feat: add login request DTO"
  "feat: add user registration request DTO"
  "feat: add user response DTO"
  "feat: add click event entity for URL analytics"
  "feat: implement URL mapping entity"
  "feat: implement user entity and role management"
  "feat: add click event repository"
  "feat: add URL mapping repository"
  "feat: add user repository"
  "feat: implement URL mapping service"
  "feat: implement Spring Security user details"
  "feat: implement custom user details service"
  "feat: define user service operations"
  "feat: implement user service business logic"
  "chore: update Spring Boot application configuration"
)

# Verify that the script is running from the repository root.
git rev-parse --show-toplevel >/dev/null

# Ensure all arrays have the same number of entries.
if [ "${#DATES[@]}" -ne "${#FILES[@]}" ] ||
   [ "${#FILES[@]}" -ne "${#MESSAGES[@]}" ]; then
  echo "Error: DATES, FILES and MESSAGES must have equal lengths."
  exit 1
fi

# Verify every target file exists before starting.
for FILE in "${FILES[@]}"; do
  if [ ! -f "$FILE" ]; then
    echo "Error: Existing file not found: $FILE"
    echo "No commits were created."
    exit 1
  fi
done

for i in "${!FILES[@]}"; do
  FILE="${FILES[$i]}"
  DATE="${DATES[$i]}"
  MSG="${MESSAGES[$i]}"

  # Stage only the existing file.
  git add -- "$FILE"

  # Skip if this file has no staged changes.
  if git diff --cached --quiet -- "$FILE"; then
    echo "Skipped (no staged changes): $FILE"
    continue
  fi

  # Commit with the requested author and committer dates.
  GIT_AUTHOR_DATE="$DATE" \
  GIT_COMMITTER_DATE="$DATE" \
    git commit --date="$DATE" -m "$MSG"

  echo "Committed: $FILE @ $DATE"
done

echo ""
echo "Done! Verify with: git log --oneline --date=iso"
