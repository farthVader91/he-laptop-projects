from django.conf.urls import patterns, url

from django.contrib import admin
admin.autodiscover()

urlpatterns = patterns(
    '',
    url(r'^$', 'demo.views.index', name='index'),
    url(r'^attrs/$', 'demo.views.attrs', name='attrs'),
    url(r'^vishal/metadata/$', 'demo.views.metadata', name='metadata'),
)
