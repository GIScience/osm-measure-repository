#!/usr/bin/env python

import argparse
import os
import re
import shutil

parser = argparse.ArgumentParser(description='Handle the measures of the OSM Measure Repository.')
parser.add_argument('--add', nargs=1, metavar='NameOfMeasure', help='add a measure')
parser.add_argument('--remove', nargs=1, metavar='NameOfMeasure', help='remove a measure')
args = parser.parse_args()

if args.add is None and args.remove is None:
    parser.print_help()

## HELPING FUNCTIONS
def rewriteFile(file, f):
    with open(file, 'r') as fi:
        content = f(fi.read().decode('utf-8'))
    with open(file, 'w') as fi:
        fi.write(content.encode('utf-8'))

## ADD
if args.add is not None:
    measure = args.add[0]
    if os.path.exists(measure):
        print('''============================ ERROR ===============================
The measure exists already.
==================================================================''')
        exit()
    if not measure.startswith('Measure'):
        print('''============================ ERROR ===============================
The name of the measure must start with \'Measure\'.  Please check
for the corresponding name in the OSM Measure Repository.  The
name is shown when importing the measure.  For more information,
please check the README.md
==================================================================''')
        exit()
    rewriteFile('pom.xml', lambda content: re.sub('([ \t]+</modules>)', '        <module>' + measure + '</module>\n\g<1>', content))
    shutil.copytree('.template', measure)
    rewriteFile(os.path.join(measure, 'pom.xml') , lambda content: content.replace('{{MeasureName}}', measure))
    rewriteFile(os.path.join(measure, 'src/main/java/org/giscience/osmMeasures/repository/Measure.java') , lambda content: content.replace('{{MeasureName}}', measure))
    shutil.move(os.path.join(measure, 'src/main/java/org/giscience/osmMeasures/repository/Measure.java'), os.path.join(measure, 'src/main/java/org/giscience/osmMeasures/repository/' + measure + '.java'))
    print('done')

## REMOVE
if args.remove is not None:
    measure = args.remove[0]
    print('=========================== CAUTION ==============================')
    answer = raw_input('Are you sure to delete the directory "' + measure + '"?\n[YES/NO]: ')
    if answer != 'YES':
        print('cancelling request ...')
        exit()
    rewriteFile('pom.xml', lambda content: re.sub('[ \t\n]*<module>' + measure + '</module>', '', content))
    try:
        shutil.rmtree(measure)
    except:
        pass
    print('done')
