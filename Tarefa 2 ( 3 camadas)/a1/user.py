import socket

#cria o socket
cliente = socket.socket()
host = '127.0.0.1'
port = 2021

#conecta no servidor pela porta 2021
try:
    cliente.connect((host, port))
except socket.error as e:
    print(str(e))

#le o nome
nome = input('Digite o nome: ')

#envia o nome pro servidor de aplicacao
cliente.send(str.encode(nome+"\n"))

#pega mensagem do servidor
res = cliente.recv(1024)

#mostra mensagem
print(res.decode('utf')[2:])

cliente.close()