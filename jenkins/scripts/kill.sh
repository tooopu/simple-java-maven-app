set -x
cat .pid
kill $(cat .pid)