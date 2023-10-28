FROM quay.io/keycloak/keycloak:22.0.4 as builder

# Enable health and metrics support
ENV KC_HEALTH_ENABLED=true
ENV KC_METRICS_ENABLED=true

ENV KC_DB=postgres
ENV KC_DB_URL=jdbc:postgresql://dpg-cktflgm5or3s7387rrfg-a/keycloakdb_xxug
ENV KC_DB_URL_PORT=5432
ENV KC_DB_USERNAME=keycloakdb_user
ENV KC_DB_URL_DATABASE=keycloakdb_xxug
ENV KC_DB_URL_HOST=dpg-cktflgm5or3s7387rrfg-a
ENV KC_DB_PASSWORD=V9TvvyRJCZr7hSHj7rQ9tCqlqYteRxw0
ENV KC_HOSTNAME_URL=https://keycloak-00hq.onrender.com/
ENV KC_HOSTNAME_ADMIN_URL=https://keycloak-00hq.onrender.com/
ENV KC_HTTP_ENABLED=true

EXPOSE 8080
ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]
CMD ["start"]