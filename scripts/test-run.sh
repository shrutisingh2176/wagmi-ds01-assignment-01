#!/usr/bin/env bash
set -e

# One-shot, student-friendly test runner with concise summary and tips

ROOT_DIR="$(cd "$(dirname "$0")"/.. && pwd)"
cd "$ROOT_DIR"

# Colors
if command -v tput >/dev/null 2>&1; then
  GREEN="$(tput setaf 2)"; RED="$(tput setaf 1)"; YELLOW="$(tput setaf 3)"; BLUE="$(tput setaf 4)"; BOLD="$(tput bold)"; RESET="$(tput sgr0)"
else
  GREEN=""; RED=""; YELLOW=""; BLUE=""; BOLD=""; RESET=""
fi

PATTERN="$1"
if [ -n "$PATTERN" ]; then
  MVN_FILTER="-Dtest=$PATTERN"
  echo -e "${BLUE}${BOLD}Running filtered tests${RESET}: $PATTERN"
else
  echo -e "${BLUE}${BOLD}Running all tests...${RESET}"
fi

START_TS=$(date +%s)
if mvn -q -DtrimStackTrace=true -Dsurefire.useFile=true $MVN_FILTER test >/dev/null; then
  STATUS="PASS"
else
  STATUS="FAIL"
fi
END_TS=$(date +%s)
ELAPSED=$((END_TS-START_TS))

echo
echo "${BOLD}================ Test Summary =================${RESET}"
if command -v rg >/dev/null 2>&1; then
  rg -n "^Tests run:" target/surefire-reports \
    | sed 's#target/surefire-reports/##' \
    | while IFS= read -r line; do echo "$line"; echo ""; done
else
  grep -n "^Tests run:" target/surefire-reports/*.txt \
    | sed 's#target/surefire-reports/##' \
    | while IFS= read -r line; do echo "$line"; echo ""; done
fi
echo "${BOLD}===============================================${RESET}"

# Totals (best-effort)
if command -v rg >/dev/null 2>&1; then
  TOTALS=$(rg "^Tests run:" -N target/surefire-reports/*.txt | awk -F',' '{gsub(/[^0-9]/, "", $1); tr+=$1; gsub(/[^0-9]/, "", $2); fl+=$2; gsub(/[^0-9]/, "", $3); er+=$3} END{print tr, fl, er}')
else
  TOTALS=$(grep "^Tests run:" target/surefire-reports/*.txt | awk -F',' '{gsub(/[^0-9]/, "", $1); tr+=$1; gsub(/[^0-9]/, "", $2); fl+=$2; gsub(/[^0-9]/, "", $3); er+=$3} END{print tr, fl, er}')
fi
read -r TR FL ER <<< "$TOTALS"

if [ "$STATUS" = "PASS" ]; then
  echo -e "${GREEN}${BOLD}OVERALL: PASS${RESET}   Total: $TR   Failures: $FL   Errors: $ER   Time: ${ELAPSED}s"
else
  echo -e "${RED}${BOLD}OVERALL: FAIL${RESET}   Total: $TR   Failures: $FL   Errors: $ER   Time: ${ELAPSED}s"
  echo
  echo -e "${YELLOW}${BOLD}Failing suites:${RESET}"
  if command -v rg >/dev/null 2>&1; then
    rg -n "Failures: [1-9]|Errors: [1-9]" target/surefire-reports \
      | sed 's#target/surefire-reports/##' \
      | while IFS= read -r line; do echo "$line"; echo ""; done
  else
    grep -nE "Failures: [1-9]|Errors: [1-9]" target/surefire-reports/*.txt \
      | sed 's#target/surefire-reports/##' \
      | while IFS= read -r line; do echo "$line"; echo ""; done
  fi
  echo
  echo -e "${BOLD}Tip:${RESET} Run a single test class or method, e.g.:"
  echo "  ./scripts/test-run.sh test.java.com.wagmi.finance.alg.SortersTest#mergeSortByDate_sorts_by_date_ascending"
  echo "  mvn -q -Dtest=test.java.com.wagmi.finance.alg.SortersTest test"
fi

echo
echo -e "${BOLD}Open details:${RESET} target/surefire-reports/<Suite>.txt"
exit 0


