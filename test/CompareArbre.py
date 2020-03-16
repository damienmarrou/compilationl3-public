import sys
def main():

    input_directory = 'test/input/'
    file_name = sys.argv[1]
    input_file = input_directory + f'{file_name}'

    ref_directorySA = 'test/sa-ref/'
    ref_directoryTS = 'test/ts-ref/'
    ref_directoryC3A = 'test/c3a-ref/'
    ref_directoryPRENASM = 'test/prenasm-ref/'

    if ('.sa' in file_name):
        ref_file = ref_directorySA + f'{file_name}'
    else:
        if ('.c3a' in file_name):
            ref_file = ref_directoryC3A + f'{file_name}'
        else:
            if ('.ts' in file_name):
                ref_file = ref_directoryTS + f'{file_name}'
            else:
                ref_file = ref_directoryPRENASM + f'{file_name}'

    if(len(sys.argv)!=2):
        print("pas le bon argument")
        haveError = False
    try:
        input_f = open(input_file, 'r')
        ref_f = open(ref_file, 'r')
        input_line = input_f.readline()
        ref_line = ref_f.readline()
        nbErreur = 0
        while input_line:
            # print(input_line +"\t"+ref_line,sys.stderr)
            if (input_line != ref_line):
                if (haveError == False):
                    haveError = True
                    print(input_file, file=sys.stderr)
                nbErreur = nbErreur + 1
                print("input :" + input_line + "\nref   :" + ref_line + "\n", file=sys.stderr)
            input_line = input_f.readline()
            ref_line = ref_f.readline()
        if (nbErreur > 0):
            print("nb error in file : ", nbErreur, file=sys.stderr)
            print("____________________________________________________________", file=sys.stderr)
        # with open(input_file,'r') as f:
        #   input_f = f.read()
        # with open(ref_file,'r') as f:
        #   ref_f = f.read()
        # if input_f !=ref_f:
        #  print("erreur dans le fichier "+file_name+'\n',file=sys.stderr)
        # print(input_f == ref_f,'\t',"input" + input_f,'\n',"ref" + ref_f,file=sys.stderr)

    except Exception as e:
        print(e,file=sys.stderr)

if __name__ == '__main__':
    main()