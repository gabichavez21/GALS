#!/usr/bin/env python3
import random

months = {
    1: "January",
    2: "February",
    3: "March",
    4: "April",
    5: "May",
    6: "June",
    7: "July",
    8: "August",
    9: "September",
    10: "October",
    11: "November",
    12: "December"
}

monthDays = {
    "January"   :  31,
    "February"  :  28,
    "March"     :  31,
    "April"     :  30,
    "May"       :  31,
    "June"      :  30,
    "July"      :  31,
    "August"    :  31,
    "September" :  30,
     "October"  :  31,
     "November" :  30,
     "December" :  31
}

def main():

    files = [None] * 10
    numFiles = len(files)
    numTasks = 10

    for i in range(0, numFiles):
        files[i] = ("Input" + str(i) + ".txt")

    for textFile in files:
        filepath = "./input/" + textFile
        f = open(filepath, 'w+')
        for j in range(0, numTasks):
            # task name
            f.write("Task" + str(j) + "\n")

            # task time
            f.write(str(random.randrange(1, 1000)) + "\n")

            # task priority
            f.write(str(random.randint(1, 10)) + "\n")

            # due date month
            monthNum = random.randint(1, 12)
            month = months[monthNum]
            f.write(month + "\n")

           # day of month
            maxDays = monthDays[month]
            monthDay = random.randint(1, maxDays)
            f.write(str(monthDay) + "\n")

            # year
            f.write("2020\n")

            if j < numTasks - 1:
                f.write("y\n")

        f.write("n")
        f.close()


if __name__ == "__main__":
    main()
