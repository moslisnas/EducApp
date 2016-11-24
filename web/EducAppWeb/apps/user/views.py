import json

from rest_framework.views import APIView

from django.http import HttpResponse
from django.contrib.auth.models import User

from apps.user.serializers import UserSerializer
# Create your views here.
class UserAPI(APIView):
    serializer = UserSerializer

    def get(self, request, format=None):
        list = User.objects.all()
        response = self.serializer(list, many=True)

        return HttpResponse(json.dumps(response.data), content_type='application/json')