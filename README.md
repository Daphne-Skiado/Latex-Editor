# Latex-Editor

Java application of a simple LaTeX editor capable of writing and maintaining LaTeX documents.

Available document templates:
- Report
- Book
- Article
- Letter
- Empty document

Available functionalities:
- Creation of a Latex document based on the templates above.
- Editing of a document and addition of specific Latex commands via the application's user interface.
- Automatic version tracking mechanism to rollback to previous document's versions
- Saving ang loading the Latex document from disk storage.

The architecture of the project follows the Model-View-Controller pattern
- model: this package comprises all the classes that are responsible for the representation and
the management of the documents.
- view: this package includes all the classes that are responsible for the visualization of the
documents and the interaction with the user.
- controller: this package includes classes that control the data flow between the model and the
view elements. In other words, these classes realize the reactions of the application to the user
input.
