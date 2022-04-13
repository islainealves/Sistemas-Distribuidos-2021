from xmlrpc.server import SimpleXMLRPCServer
import psycopg2

#funcao pra pesquisar nome no banco de dados
def procuraNome(nome):
    con = psycopg2.connect(
        host='localhost', database='ListaSD', user='postgres', password='2859')
    cur = con.cursor()
    sql = "SELECT saldo FROM public.exercicio8 WHERE nome='" + nome + "';"
    cur.execute(sql)
    con.commit()
    recset = cur.fetchall()
    saldo = 0
    for rec in recset:
        saldo=(rec[0])
    con.close()
    return saldo

#registra a funcao procura nome
server = SimpleXMLRPCServer(("172.31.94.227", 4000))
server.register_function(procuraNome, 'procuraNome')

if __name__ == '__main__':
    try:
        print('Serving...')
        server.serve_forever()
    except KeyboardInterrupt:
        print('Exiting')