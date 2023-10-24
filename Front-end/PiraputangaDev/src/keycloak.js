import Keycloak from "keycloak-js";

let initOptions = {
  url: 'http://localhost:8080/',
  realm: 'PiraputangaDev',
  clientId: 'frontend-client',
  onLoad: "login-required"
};

export default new Keycloak(initOptions);