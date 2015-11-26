{% extends "catalog.html" %}

{% block name %}
	{% if request.session.lang = 'en-us'%}
		{{ c.name }}
	{% else %}
		{{ c.r_name }}
	{% endif %}
{% endblock %}



{% block products %}

	<div class="col-xs-9">
	{% for p in products %}
	<div class = "row-xs-9">
		<div class = "col-xs-7">
			<img src = "{{ p.image.url }}" alt = "{{ p.name }}"/>
		</div>
		<div class = "col-xs-5">
			<p> {% if request.session.lang = 'en-us'%} Name: {% else %} Название: {% endif %}{{ p.name }}</p>
			<p>{% if request.session.lang = 'en-us'%} Price:  {% else %} Цена: {% endif %} $ {{ p.price }} </p>
			<p><a href = "{{ p.get_absolute_url }}" class="btn btn-default">{% if request.session.lang = 'en-us'%}  More.. {% else %} Подробнее... {% endif %}</a></p>
		</div>
	</div>
	{% endfor %}
	</div>

{% endblock %}