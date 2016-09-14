package co.jgrs.casm.git.gitclient;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

public class GitSimpleClient {

    public static void main(String[] args) throws Exception {
        Repository repo = new FileRepositoryBuilder().setGitDir(new File("C:/projects/webster/.git")).build();
//        Ref develop = repo.getRef("develop");
//        ObjectId developTip = develop.getObjectId();
//        ObjectLoader loader = repo.open(developTip);
//        loader.copyTo(System.out);
        
//        try (Git git = new Git(repo)) {
//            Iterable<RevCommit> logs = git.log().call();
//            int count = 0;
//            for (RevCommit rev : logs) {
//                System.out.println(rev + ", name: " + rev.getName() + ", id: " + rev.getId().getName() );
//                count++;
//            }
//            System.out.println("Had " + count + " commits overall on current branch");
//        }
        
//        ObjectId oldHead = repo.resolve("HEAD^^^^{tree}");
//        ObjectId head = repo.resolve("HEAD^{tree}");
//        System.out.println("Showing the diff between: " + oldHead + " and " + head);
//        try (ObjectReader reader = repo.newObjectReader()) {
//            CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
//            oldTreeIter.reset(reader, oldHead);
//            CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
//            newTreeIter.reset(reader, head);
//            // finally get the list of changed files
//            try (Git git = new Git(repo)) {
//                List<DiffEntry> diffs= git.diff()
//                                    .setNewTree(newTreeIter)
//                                    .setOldTree(oldTreeIter)
//                                    .call();
//                for (DiffEntry entry : diffs) {
//                    System.out.println("Entry: " + entry);
//                }
//            }
//        }

        AbstractTreeIterator oldTreeParser = prepareTreeParser(repo, "592e9cd4331dde08d41d535d6bdc649900d1b92e");
        AbstractTreeIterator newTreeParser = prepareTreeParser(repo, "f371cf5e5af7d07171c0f459a80b516cdfecd2f6");

        // then the procelain diff-command returns a list of diff entries
        try (Git git = new Git(repo)) {
            List<DiffEntry> diff = git.diff().
                    setOldTree(oldTreeParser).
                    setNewTree(newTreeParser).
//                    setPathFilter(PathFilter.create("CORP_PSO_SRC/Actuate/Reports/AchPositivepay.bas")).
                    call();
            for (DiffEntry entry : diff) {
                System.out.println("Entry: " + entry + ", from: " + entry.getOldId() + ", to: " + entry.getNewId());
                try (DiffFormatter formatter = new DiffFormatter(System.out)) {
                    formatter.setRepository(repo);
                    formatter.format(entry);
                }
            }
        }
    }

    private static AbstractTreeIterator prepareTreeParser(Repository repository, String objectId) throws IOException,
            MissingObjectException, IncorrectObjectTypeException {
        // from the commit we can build the tree which allows us to construct the TreeParser
        try (RevWalk walk = new RevWalk(repository)) {
            RevCommit commit = walk.parseCommit(ObjectId.fromString(objectId));
            RevTree tree = walk.parseTree(commit.getTree().getId());
        
            CanonicalTreeParser oldTreeParser = new CanonicalTreeParser();
            try (ObjectReader oldReader = repository.newObjectReader()) {
                oldTreeParser.reset(oldReader, tree.getId());
            }
        
            walk.dispose();
        
            return oldTreeParser;
        }
    }

}
