window.onload = function() {
  //<editor-fold desc="Changeable Configuration Block">

  // the following lines will be replaced by docker/configurator, when it runs in a docker-container
  window.ui = SwaggerUIBundle({
	urls: [
      {url: "http://localhost:8000/rest/model/v0.0.1/openapi.json", name: "Repository Access and Model API"},
      {url: "http://localhost:8000/rest/management/v0.0.1/openapi.json", name: "Project and Repository Management API"},
    ],
    //url: "http://localhost:8000/rest/model/v0.0.1/openapi.json",
    dom_id: '#swagger-ui',
    deepLinking: true,
    presets: [
      SwaggerUIBundle.presets.apis,
      SwaggerUIStandalonePreset
    ],
    plugins: [
      SwaggerUIBundle.plugins.DownloadUrl
    ],
    layout: "StandaloneLayout",
	syntaxHighlight: true,
  });

  //</editor-fold>
};
