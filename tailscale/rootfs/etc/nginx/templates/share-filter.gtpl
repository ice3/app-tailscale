server {
    listen 127.0.0.1:{{ .port }} default_server;

    include /etc/nginx/includes/server_params.conf;
{{ range .paths }}
    location = {{ . }} {
        proxy_pass {{ $.upstream }};
        include /etc/nginx/includes/share_proxy_params.conf;
    }

    location ^~ {{ . }}/ {
        proxy_pass {{ $.upstream }};
        include /etc/nginx/includes/share_proxy_params.conf;
    }
{{ end }}
    location / {
        return 404;
    }
}
