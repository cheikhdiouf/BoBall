#!/bin/bash
# wait-for-postgres.sh

set -e

host="$1"
shift

echo "Attente de la base de données PostgreSQL à $host:5432..."

until pg_isready -h "$host" -p 5432; do
  echo "En attente de PostgreSQL..."
  sleep 2
done

echo "PostgreSQL est prêt ! Lancement du backend..."
exec "$@"
