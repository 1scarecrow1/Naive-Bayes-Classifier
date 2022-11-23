# Naive Bayes Classifier

Identifying spam manually is cumbersome. With a simple binary classification system based on Bayesian statistics, this can be automated. Consider an unclassified document consisting of n words each, such that the random variables $X1, . . . , Xn$ represent the $n$ words in the document (for example, $X3$ is the random variable which generates the 3th word in the document). Also, let us assume that the respective realizations of $X1, . . . , Xn$, given as $x1, . . . , xn$ can take values from the word set $W = {w1, . . . , wk}$. Furthermore, define the random variable $S$ with realization $s$ as follows:

$$S = { 0 if the document is spam 1 if the document is not spam $$

Plausibly, the number of spam emails can be considered to be lower than non-spam emails.
$$P (S = 1|X1 = x1, . . . , Xn = xn) > P (S = 0|X1 = x1, . . . , Xn = xn)$$

Using Bayes' theorem,
$$P (S = 1)P (X1 = x1, . . . , Xn = xn|S = 1) > P (S = 0)P (X1 = x1, . . . , Xn = xn|S = 0)$$

Assume $X1, X2,.., Xn$ are $i.i.d.$ such that
$$P (S = 1) n∏i=1 P (X = xi|S = 1) > P (S = 0) n∏ i=1 P (X = xi|S = 0)$$

Hence
$$P (S = 1) = 1 − ̂P (S = 0) = total # of spam documents total # of documents$$

NaiveBayes.java builds on this idea to to classify emails.
Utility Methods: WordCounter.java counts the number of times a word appears in the documents passed through it. It also contains methods to compute the probability that a document is spam.
ConfusionMatrix.java contains methods to assess classification accuracy.


