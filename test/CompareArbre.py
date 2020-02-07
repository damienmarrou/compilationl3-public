import sys
def main():

    input_directory = 'test/input/'
    file_name = sys.argv[1]
    input_file = input_directory + f'{file_name}'

    ref_directorySA = 'test/sa-ref/'
    ref_directoryTS = 'test/ts-ref/'

    if(file_name[-1]=='a'):
        ref_file = ref_directorySA + f'{file_name}'
    else:
        ref_file = ref_directoryTS + f'{file_name}'


    if(len(sys.argv)!=2):
        print("pas le bon argument")
    try:
        with open(input_file,'r') as f:
            input_f = f.read()
        with open(ref_file,'r') as f:
            ref_f = f.read()
        if input_f !=ref_f:
            print("erreur dans le fichier "+file_name+'\n',file=sys.stderr)
        #    print(input_f == ref_f,'\t',input_f,'\n',ref_f,file=sys.stderr)

    except Exception as e:
        print(e,file=sys.stderr)

if __name__ == '__main__':
    main()