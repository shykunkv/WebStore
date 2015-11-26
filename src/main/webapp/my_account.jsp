{% extends "catalog.html" %}

{% block content %}
	<strong>
		{% if request.session.lang = 'en-us'%}
			Welcome, 
		{% else %}
			Добро пожаловать, 
		{% endif %}	

		{{  name }} 
	</strong>
{% endblock %}