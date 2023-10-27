import Keycloak from "keycloak-js";

let initOptions = {
  url: 'https://keycloak-4208.onrender.com/',
  realm: 'PiraputangaDev',
  clientId: 'frontend-client',
  onLoad: "login-required"
};

export default new Keycloak(initOptions);