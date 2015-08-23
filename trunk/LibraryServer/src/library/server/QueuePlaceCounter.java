/*
 * QueuePlaceCounter.java
 *
 * Created on 23 marzec 2007, 18:57
 */

package library.server;

import java.util.HashMap;
import java.util.Map;

/**
 * finds place of element in linked list given online.
 *
 * Example:
 * element number is 8
 * queue is 33->2->14->8->20
 * pairs come randomly, eg. (2,14), (8,20), (14,8), (20,77), (33,2)
 ** result is 2
 *
 * @author Piotrek
 */
class QueuePlaceCounter {
    private int featuredElement;
    
    private Begin featuredElementLink;
    
    private Map<Integer,Begin> begins;
    private Map<Integer,End> ends;
    
    public QueuePlaceCounter(int featuredElement) {
        this.featuredElement = featuredElement;
        this.featuredElementLink = null;
        
        begins = new HashMap<Integer,Begin>();
        ends = new HashMap<Integer,End>();
    }
    
    public void handlePair(int number, int link) {
        if (link == featuredElement)
            return; // do not add anything before feat. elm.
        
        End y1 = ends.get(number);
        Begin x2 = begins.get(link);
        
        // x->y->z->number (y1)    number->link   link->c->d->e->f->g (x2)
        if (y1 != null && x2 != null && number != featuredElement) {
            Begin x1 = y1.toBegin;
            End y2 = x2.toEnd;
            
            begins.remove(x2.hashCode());
            ends.remove(y1.hashCode());
            
            x1.end = y2.end;
            y2.begin = x1.begin;
            x1.toEnd = y2;
            y2.toBegin = x1;
            x1.length += x2.length + 1;
        } else if (x2 != null) { // number->link   link->c->d->e (x2)
            y1 = x2.toEnd;
            y1.begin = number;
            begins.remove(x2.hashCode());
            x2.begin = number;
            ++ x2.length;
            begins.put(x2.hashCode(), x2);
            
            if (number == featuredElement)
                featuredElementLink = x2;
        } else if (y1 != null) { // x->y->z->number (y1)   number->link
            if (number == featuredElement)
                return;
            
            x2 = y1.toBegin;
            ++ x2.length;
            x2.end = link;
            ends.remove(y1.hashCode());
            y1.end = link;
            ends.put(y1.hashCode(), y1);
        } else {
            Begin newBegin = new Begin(number,link);
            End newEnd = newBegin.createEnd();
            begins.put(newBegin.hashCode(), newBegin);
            ends.put(newEnd.hashCode(), newEnd);
            
            if (number == featuredElement)
                featuredElementLink = newBegin;
        }
    }
    
    /** returns counted place in queue
     *@return 0 if featuredElement hasn't appeared in any of pairs,
     *positive number otherwise
     */
    public int getPlace() {
        return (featuredElementLink == null) ? 0 : featuredElementLink.length;
    }
    
    /** stores begin of a list fragment and counts its length */
    static class Begin {
        public int begin, end;
        public End toEnd;
        public int length;
        
        public Begin(int begin, int end) {
            this.begin = begin;
            this.end = end;
            length = 1;
        }
        
        public End createEnd() {
            toEnd = new End(begin, end, this);
            return toEnd;
        }

        public int hashCode() {
            return begin;
        }
        
    }
   
    /** stores end of a list fragment */
    static class End {
        int begin, end;
        public Begin toBegin;
        
        public End(int begin, int end, Begin toBegin) {
            this.begin = begin;
            this.end = end;
            this.toBegin = toBegin;
        }

        public int hashCode() {
            return end;
        }

    }
}
