/**
 * @file filler.cpp
 * Implementation of functions in the filler namespace. Heavily based on
 * old MP4 by CS225 Staff, Fall 2010.
 *
 * @author Chase Geigle
 * @date Fall 2012
 */
#include "filler.h"

animation filler::dfs::fillSolid(PNG& img, int x, int y, RGBAPixel fillColor,
                                 int tolerance, int frameFreq)
{
    /**
     * @todo Your code here! You should replace the following line with a
     * correct call to fill with the correct colorPicker parameter.
     */
     solidColorPicker a(fillColor); 
    return filler::dfs::fill(img, x, y, a, tolerance, frameFreq);
}

animation filler::dfs::fillGrid(PNG& img, int x, int y, RGBAPixel gridColor,
                                int gridSpacing, int tolerance, int frameFreq)
{
    /**
     * @todo Your code here! You should replace the following line with a
     * correct call to fill with the correct colorPicker parameter.
     */
    gridColorPicker a(gridColor, gridSpacing);
    return filler::dfs::fill(img, x, y, a, tolerance, frameFreq);
}

animation filler::dfs::fillGradient(PNG& img, int x, int y,
                                    RGBAPixel fadeColor1, RGBAPixel fadeColor2,
                                    int radius, int tolerance, int frameFreq)
{
    /**
     * @todo Your code here! You should replace the following line with a
     * correct call to fill with the correct colorPicker parameter.
     */
     gradientColorPicker a(fadeColor1, fadeColor2, radius, x, y);
    return filler::dfs::fill(img, x, y, a, tolerance, frameFreq);
}

animation filler::dfs::fill(PNG& img, int x, int y, colorPicker& fillColor,
                            int tolerance, int frameFreq)
{
    /**
     * @todo Your code here! You should replace the following line with a
     * correct call to filler::fill with the correct template parameter
     * indicating the ordering structure to be used in the fill.
     */
    return filler::fill<Stack>(img, x, y, fillColor, tolerance, frameFreq);
}

animation filler::bfs::fillSolid(PNG& img, int x, int y, RGBAPixel fillColor,
                                 int tolerance, int frameFreq)
{
    /**
     * @todo Your code here! You should replace the following line with a
     * correct call to fill with the correct colorPicker parameter.
     */
    solidColorPicker a(fillColor); 
    return filler::bfs::fill(img, x, y, a, tolerance, frameFreq);
}

animation filler::bfs::fillGrid(PNG& img, int x, int y, RGBAPixel gridColor,
                                int gridSpacing, int tolerance, int frameFreq)
{
    /**
     * @todo Your code here! You should replace the following line with a
     * correct call to fill with the correct colorPicker parameter.
     */
    gridColorPicker a(gridColor, gridSpacing);
    return filler::bfs::fill(img, x, y, a, tolerance, frameFreq);
}

animation filler::bfs::fillGradient(PNG& img, int x, int y,
                                    RGBAPixel fadeColor1, RGBAPixel fadeColor2,
                                    int radius, int tolerance, int frameFreq)
{
    /**
     * @todo Your code here! You should replace the following line with a
     * correct call to fill with the correct colorPicker parameter.
     */
    gradientColorPicker a(fadeColor1, fadeColor2, radius, x, y);
    return filler::bfs::fill(img, x, y, a, tolerance, frameFreq);
}

animation filler::bfs::fill(PNG& img, int x, int y, colorPicker& fillColor,
                            int tolerance, int frameFreq)
{
    /**
     * @todo Your code here! You should replace the following line with a
     * correct call to filler::fill with the correct template parameter
     * indicating the ordering structure to be used in the fill.
     */
    return filler::fill<Queue>(img, x, y, fillColor, tolerance, frameFreq);
}

template <template <class T> class OrderingStructure>
animation filler::fill(PNG& img, int x, int y, colorPicker& fillColor,
                       int tolerance, int frameFreq)
{
    /**
     * @todo You need to implement this function!
     *
     * This is the basic description of a flood-fill algorithm: Every fill
     * algorithm requires an ordering structure, which is passed to this
     * function via its template parameter. For a breadth-first-search
     * fill, that structure is a Queue, for a depth-first-search, that
     * structure is a Stack. To begin the algorithm, you simply place the
     * given point in the ordering structure. Then, until the structure is
     * empty, you do the following:
     *
     * 1.     Remove a point from the ordering structure.
     *
     *        If it has not been processed before and if its color is
     *        within the tolerance distance (up to and **including**
     *        tolerance away in square-RGB-space-distance) to the original
     *        point's pixel color [that is, \f$(currentRed - OriginalRed)^2 +
              (currentGreen - OriginalGreen)^2 + (currentBlue -
              OriginalBlue)^2 \leq tolerance\f$], then:
     *        1.    indicate somehow that it has been processed (do not mark it
     *              "processed" anywhere else in your code)
     *        2.    change its color in the image using the appropriate
     *              colorPicker
     *        3.    add all of its neighbors to the ordering structure, and
     *        4.    if it is the appropriate frame, send the current PNG to the
     *              animation (as described below).
     * 2.     When implementing your breadth-first-search and
     *        depth-first-search fills, you will need to explore neighboring
     *        pixels in some order.
     *
     *        While the order in which you examine neighbors does not matter
     *        for a proper fill, you must use the same order as we do for
     *        your animations to come out like ours! The order you should put
     *        neighboring pixels **ONTO** the queue or stack is as follows:
     *        **RIGHT(+x), DOWN(+y), LEFT(-x), UP(-y). IMPORTANT NOTE: *UP*
     *        here means towards the top of the image, so since an image has
     *        smaller y coordinates at the top, this is in the *negative y*
     *        direction. Similarly, *DOWN* means in the *positive y*
     *        direction.** To reiterate, when you are exploring (filling out)
     *        from a given pixel, you must first try to fill the pixel to
     *        it's RIGHT, then the one DOWN from it, then to the LEFT and
     *        finally UP. If you do them in a different order, your fill may
     *        still work correctly, but your animations will be different
     *        from the grading scripts!
     * 3.     For every k pixels filled, **starting at the kth pixel**, you
     *        must add a frame to the animation, where k = frameFreq.
     *
     *        For example, if frameFreq is 4, then after the 4th pixel has
     *        been filled you should add a frame to the animation, then again
     *        after the 8th pixel, etc.  You must only add frames for the
     *        number of pixels that have been filled, not the number that
     *        have been checked. So if frameFreq is set to 1, a pixel should
     *        be filled every frame.
     */
     // declare the 
     animation ret;
     //get width and height of the image
     int w = img.width();
     int h = img.height();
     // create a boolean 2d array that track whether the pixel has been processed
     // initialize every entry to false
     bool** processed = new bool*[w];
     for(int k = 0; k < w; k++)
        processed[k] = new bool[h];

     for (int i = 0; i < w; i++)
     {
        for (int j = 0; j < h; j++)
        {
            processed[i][j] = false;
        }
     }

     // an int counter for the frame processed
     unsigned long frameCounter = 0;

      // declare the orderingstructure\
     // both of these should be ordering structures
     OrderingStructure <int> xs;
     OrderingStructure <int> ys;
     // x and y coordinates to ordering structure
     xs.add(x);
     ys.add(y);
     // create 2 ints to hold value from xs and ys
     int tempX;
     int tempY;
     // get a RGBAPixel to hold fill
     RGBAPixel fill;
     RGBAPixel original = *img(x,y);

     // until ordering structure is empty
     while (!xs.isEmpty() && !ys.isEmpty())
     {
                //cout<<"reach while loop"<<endl;

        // remove point from ordering structure
        tempX = xs.remove();
        tempY = ys.remove();
        //cout<<"reach while loop2"<<endl;
     

        RGBAPixel* processing = img(tempX,tempY);
        //cout<<"reach while loop3"<<endl;
        // determine if the pixel has been processed and  
        bool withinTolerance = ((processing->red - original.red)*(processing->red - original.red) + 
                                (processing->blue - original.blue)*(processing->blue - original.blue) + 
                                (processing->green - original.green)*(processing->green - original.green)) <= tolerance;
        //cout<<processed[tempX][tempY]<<" "<<withinTolerance<<endl;
        if (!processed[tempX][tempY] && withinTolerance)
        {
            // get the appropriate fillcolor from functor
            fill = fillColor(tempX, tempY);
            // indicate that the point has been processed
            processed[tempX][tempY] = true;
            // increment frame counter
            frameCounter++;
            //change the pixel from image to fill color
            processing->red = fill.red;
            processing->green = fill.green;
            processing->blue = fill.blue;
            // push x and y based
            //**RIGHT(+x) 
            // add additional conditions
            if ((tempX+1) < w && (tempX+1) >= 0 && tempY < h && tempY >= 0)
            {
            xs.add(tempX+1);
            ys.add(tempY);
            }
            //DOWN(+y)
            if (tempX < w && tempX >= 0 && (tempY+1) < h && (tempY+1) >= 0)
            {
            xs.add(tempX);
            ys.add(tempY+1);
            }
            //LEFT(-x)
            if ((tempX-1) < w && (tempX-1) >= 0 && tempY < h && tempY >= 0)
            {
            xs.add(tempX-1);
            ys.add(tempY);
            }
            //UP(-y)
            if (tempX < w && tempX >= 0 && (tempY-1) < h && (tempY-1) >= 0)
            {
            xs.add(tempX);
            ys.add(tempY-1);
            }
            if (frameCounter % frameFreq == 0)
            {
                ret.addFrame(img);
            }
        }
    } 

    // clean dynamic memory
    for(int z = 0; z < w; ++z) 
    {
    delete [] processed[z];
    }
    delete [] processed;



    return ret;
}
