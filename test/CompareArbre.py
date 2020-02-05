import sys
def main():

    input_directory = 'test/input/'
    ref_directory = 'test/sa-ref/'

    if(len(sys.argv)!=2):
        print("pas le bon argument")
    file_name = sys.argv[1]
    input_file = input_directory + f'{file_name}'
    ref_file = ref_directory + f'{file_name}'

    try:
        with open(input_file,'r') as f:
            input_f = f.read()
        with open(ref_file,'r') as f:
            ref_f = f.read()
        if input_f !=ref_f:
            print("erreur dans le fichier "+file_name,file=sys.stderr)
            print(input_f == ref_f,'\t',input_f,ref_f,file=sys.stderr)

    except Exception as e:
        print(e,file=sys.stderr)

if __name__ == '__main__':
    main()