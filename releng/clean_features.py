from glob import glob
import os
import xml.etree.ElementTree as ElementTree

class FeatureCleaner:

    FILE_NAME = "../**/feature.xml"

    def __init__(self):
        self.feature_file_names = list()

    def clean(self):
        print('Cleaning feature.xml files...')
        print('Searching files...')
        
        print (os.listdir())

        self._find_files(FeatureCleaner.FILE_NAME)

        print('Found the following features:')
        print(*self.feature_file_names, sep='\n')

        self._process_features()

    def _find_files(self, file_name_pattern):
        
        self.feature_file_names = glob(file_name_pattern)
        return self.feature_file_names

    def _process_features(self):
        for feature_file_name in self.feature_file_names:
            print('Cleaning file: ', feature_file_name, '...', end='')
            FeatureFile(feature_file_name).clean()
            print('done')
 
 
class FeatureFile:

    def __init__(self, file_name) :
        self.file_name = file_name

    def _read_feature(self):
        self.file_tree = ElementTree.parse(self.file_name)
        self.root_tree = self.file_tree.getroot()
        
    def _clean_feature(self):
        requires = self.root_tree.find('requires')

        # get all imports in the feature        
        if requires is not None:
            _org_imports=list(requires.iter('import'))

            # Loop over all imports and get rid of
            # the version and matching part
            for _import in _org_imports:
               _import.attrib.pop('version', None)
               _import.attrib.pop('match', None)
             
            # now sort the list
            def _get_element_key(element):
                retval = element.get('plugin')
                if retval is None:
                    retval = element.get('feature')
                return retval
            
            _imports = sorted(_org_imports, key=_get_element_key)

            # rmeove duplicate entries
            _unique_imports = list()
            seen = set()
            for _import in _imports:
                _import_key = _get_element_key(_import)
                if not _import_key in seen:
                    _unique_imports.append(_import)
                    seen.add(_import_key)
            
            for _import in _org_imports:
                requires.remove(_import)

            for _import in _unique_imports:
                requires.append(_import)

    def _store_feature(self):
        self.file_tree.write(self.file_name)

    def clean(self):
        self._read_feature()
        self._clean_feature()
        self._store_feature()
    


if __name__ == '__main__':
    # First change the directory to be at the root of Virtual Satellite

    print('Current working directory: ', os.getcwd())

    feature_cleaner = FeatureCleaner()
    feature_cleaner.clean()