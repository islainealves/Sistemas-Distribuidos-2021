import zmq

def main():
    # Prepare our context and publisher
    context = zmq.Context()
    subscriber = context.socket(zmq.SUB)
    subscriber.connect("tcp://172.31.81.10:4000")
    subscriber.setsockopt(zmq.SUBSCRIBE, b"1")
    subscriber.setsockopt(zmq.SUBSCRIBE, b"2")
    subscriber.setsockopt(zmq.SUBSCRIBE, b"3")
    subscriber.setsockopt(zmq.SUBSCRIBE, b"4")
    subscriber.setsockopt(zmq.SUBSCRIBE, b"5")
    subscriber.setsockopt(zmq.SUBSCRIBE, b"6")
    subscriber.setsockopt(zmq.SUBSCRIBE, b"7")
    subscriber.setsockopt(zmq.SUBSCRIBE, b"8")


    while True:
        # Read envelope with address
        [address, contents] = subscriber.recv_multipart()
        if address == b"1":
            valores = str(contents)[2:-1].split("@", 2)
            nome = valores[0]
            cargo = valores[1]
            salario = float(valores[2])

            if cargo == "operador":
                salario = (salario * 1.2)
            elif cargo == "programador":
                salario = (salario * 1.18)

            print("EXERCICIO 1")
            print("NOME:", nome, "\nCARGO:", cargo, "\nSALARIO REAJUSTADO:", salario)
            print("++++++++++++++++++++++++++++")

        elif address == b"2":
            valores = str(contents)[2:-1].split("@", 2)
            nome = valores[0]
            sexo = valores[1]
            idade = int(valores[2])

            if sexo == "masculino" and idade >= 18:
                mensagem = "Maior de idade"
            elif sexo == "feminino" and idade >= 21:
                mensagem = "Maior de idade"
            else:
                mensagem = "Menor de idade"

            print("EXERCICIO 2")
            print("NOME:", nome, "\nSEXO:", sexo, "\nIDADE:", idade, "\n"+mensagem)
            print("++++++++++++++++++++++++++++")


        elif address == b"3":
            valores = str(contents)[2:-1].split("@", 2)
            n1 = float(valores[0])
            n2 = float(valores[1])
            n3 = float(valores[2])

            media = (n1 + n2) / 2
            if media >= 7:
                resultado = "Aprovado"
            elif media >= 3:
                media = (media+n3) / 2
                if media >= 5:
                    resultado = "Aprovado"
                else:
                    resultado = "Reprovado"
            else:
                resultado = "Reprovado"

            print("EXERCICIO 3")
            print("NOTAS:", n1, ",", n2, ",", n3, "\nMEDIA:", media, "\nRESULTADO:", resultado)
            print("++++++++++++++++++++++++++++")


        elif address == b"4":
            valores = str(contents)[2:-1].split("@", 1)
            altura = float(valores[0])
            sexo = valores[1]

            if sexo == "feminino":
                pesoideal = (62.1 * altura) - 44.7
            elif sexo == "masculino":
                pesoideal = (72.7 * altura) - 58

            print("EXERCICIO 4")
            print("ALTURA:", altura, "\nSEXO:", sexo, "\nPESO IDEAL:", pesoideal)
            print("++++++++++++++++++++++++++++")


        elif address == b"5":
            idade = int(str(contents)[2:-1])

            if idade >= 5 and idade <= 7:
                categoria = "infantil A"
            elif idade >= 8 and idade <= 10:
                categoria = "infantil B"
            elif idade >= 11 and idade <= 13:
                categoria = "juvenil A"
            elif idade >= 14 and idade <= 17:
                categoria = "juvenil B"
            elif idade >= 18:
                categoria = "adulto"

            print("EXERCICIO 5")
            print("IDADE:", idade, "\nCATEGORIA:", categoria)
            print("++++++++++++++++++++++++++++")


        elif address == b"6":
            valores = str(contents)[2:-1].split("@", 3)
            nome = valores[0]
            nivel = valores[1]
            bruto = float(valores[2])
            dependentes = int(valores[3])

            if nivel == "A":
                if dependentes > 0:
                    liquido = bruto-(bruto * 0.08)
                else:
                    liquido = bruto-(bruto * 0.03)
            elif nivel=="B":
                if dependentes > 0:
                    liquido = bruto-(bruto * 0.1)
                else:
                    liquido = bruto-(bruto * 0.05)
            elif nivel=="C":
                if dependentes > 0:
                    liquido = bruto-(bruto * 0.15)
                else:
                    liquido = bruto-(bruto * 0.8)
            elif nivel=="D":
                if dependentes > 0:
                    liquido = bruto-(bruto * 0.17)
                else:
                    liquido = bruto-(bruto * 0.1)

            print("EXERCICIO 6")
            print("NOME:", nome, "\nNIVEL:", nivel, "\nSLARIO BRUTO:", bruto,
                  "\nNUMERO DE DEPENDENTES:", dependentes, "\nSALARIO LIQUIDO:", liquido)
            print("++++++++++++++++++++++++++++")


        elif address == b"7":
            valores = str(contents)[2:-1].split("@", 1)
            idade = int(valores[0])
            tempo = int(valores[1])

            if idade >= 65 or tempo >= 30 or (idade >= 60 and tempo >= 25):
                mensagem = "Pode aposentar"
            else:
                mensagem = "Não pode aposentar"

            print("EXERCICIO 7")
            print("IDADE:", idade, "\nTEMPO DE SERVICO:", tempo, "\n"+mensagem)
            print("++++++++++++++++++++++++++++")


        elif address == b"8":
            saldomedio = float(str(contents)[2:-1])

            if saldomedio >= 0 and saldomedio <= 200:
                credito = 0
            elif saldomedio >= 201 and saldomedio <= 400:
                credito = saldomedio * 0.2
            elif saldomedio >= 401 and saldomedio <= 600:
                credito = saldomedio * 0.3
            elif saldomedio >= 601:
                credito = saldomedio * 0.4

            print("EXERCICIO 8")
            print("SALDO MEDIO:", saldomedio, "\nPERCENTUAL DE CREDITO:", credito)
            print("++++++++++++++++++++++++++++")



    # We never get here but clean up anyhow
    subscriber.close()
    context.term()

main()