from xmlrpc.server import SimpleXMLRPCServer
import xmlrpc.client

#funcao pra calcular o percentual de credito
def calculaCredito(nome):
    #envia nome ao servidor de banco de dados e recebe o saldo medio
    s = xmlrpc.client.ServerProxy("http://172.31.94.227:4000/")
    saldomedio = float(s.procuraNome(nome))

    percentualcredito = 0
    if saldomedio>=0 and saldomedio<=200:
        percentualcredito = 0
    elif saldomedio >= 201 and saldomedio <= 400:
        percentualcredito = saldomedio * 0.2
    elif saldomedio >= 401 and saldomedio <= 600:
        percentualcredito = saldomedio * 0.3
    elif saldomedio >= 601:
        percentualcredito = saldomedio * 0.4

    return ('Percentual de Credito:', percentualcredito)

#registra a funcao calcula credito
server = SimpleXMLRPCServer(("172.31.81.10", 4000))
server.register_function(calculaCredito, 'calculaCredito')

if __name__ == '__main__':
    try:
        print('Serving...')
        server.serve_forever()
    except KeyboardInterrupt:
        print('Exiting')