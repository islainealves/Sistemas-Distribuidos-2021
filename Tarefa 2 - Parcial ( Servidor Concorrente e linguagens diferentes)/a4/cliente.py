import socket
#cria o socket
cliente = socket.socket()
host = '127.0.0.1'
port = 12345
#conecta no servidor pela porta 12345
try:
    cliente.connect((host, port))
except socket.error as e:
    print(str(e))

#le altura e sexo separadas por @
dados = input()+"@"+input()
#envia dados ao servidor
cliente.send(str.encode(dados+"\n"))
#pega mensagem do servidor
res = cliente.recv(1024)
#mostra mensagem
print(res.decode('utf'))

cliente.close()