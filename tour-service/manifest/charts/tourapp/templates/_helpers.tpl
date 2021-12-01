{{- define "labels" }}
  labels:
    generator: helm
    date: {{ now | htmlDate }}
    release: {{ .Release.Name }}
    revision: {{ .Release.Revision | quote }}
    chart: {{ .Chart.Name }}
    version: {{ .Chart.Version }}
{{- end }}
{{/*
Selector labels
*/}}
{{- define "selectorLabels" -}}
app.kubernetes.io/name: {{ .Chart.Name }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}