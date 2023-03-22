import requests
from schema import Schema, And, Use, Optional, SchemaError

url = 'https://retention-csb-test.biomed.ntua.gr/auth/realms/retention/protocol/openid-connect/token'

data = {
    "grant_type": "password",
    "username": "dev",
    "password": "qwer1234",
    "client_id": "headless"
}

resp_schema = Schema({
    'access_token': And(Use(str)),
    'expires_in': And(Use(int)),
    'refresh_expires_in': And(Use(int)),
    'refresh_token': And(Use(str)),
    'token_type': And(Use(str)),
    'not-before-policy': And(Use(int)),
    'session_state': And(Use(str)),
    'scope': And(Use(str))
})


def check(conf_schema, conf):
    try:
        conf_schema.validate(conf)
        return True
    except SchemaError:
        return False


def test_token_endpoint_returns_200():
    response = requests.post(url, headers={'Content-Type': 'application/x-www-form-urlencoded'}, data=data)
    assert response.status_code == 200


def test_token_endpoint_returns_correct_body():
    response = requests.post(url, headers={'Content-Type': 'application/x-www-form-urlencoded'}, data=data)
    body = response.json()
    assert check(resp_schema, body)
