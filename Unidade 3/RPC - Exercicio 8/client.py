import xmlrpc.client

s = xmlrpc.client.ServerProxy("http://172.31.94.227:4000/")

print('Digite o saldo medio : ')
saldomedio = int(input())

resposta = s.calculaCredito(saldomedio)

print(resposta)