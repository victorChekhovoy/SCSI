print("Pick a number between 1 and 100.")
found = False
repeats = 0
askOld = 0
hi = 100
lo = 1
while not found:
    ask = (hi + lo)//2   
    if ask == askOld:
        print("You're kidding me")
        break 
    question = "Is your number " + str(ask) + "? "
    answer = input(question)
    repeats += 1
    if answer.lower() == "yes":
        print("I guessed correctly in", repeats, "tries")
        found = True
    else:
        highLow = input("Is it higher or lower? ")
        if highLow.lower() == "higher":
            lo = ask + 1
        else:
            hi = ask - 1
    askOld = ask