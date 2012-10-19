from django.shortcuts import render_to_response
from django.template import RequestContext
from django.http import HttpResponse


def home(request):
    title = "BothellBirder"
    content = "Hello world"
    return render_to_response('base.html',
            {'title': title, 'content': content},
            context_instance=RequestContext(request))

def test(request):
    return HttpResponse("Hello world")
